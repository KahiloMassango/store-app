package com.example.store.features.checkout

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.AddressRepository
import com.example.store.core.data.repository.CartRepository
import com.example.store.core.data.repository.LocationRepository
import com.example.store.core.data.repository.OrderRepository
import com.example.store.core.model.Address
import com.example.store.core.model.MapCoordinates
import com.example.store.core.ui.util.hotFlow
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val locationRepository: LocationRepository,
    private val addressRepository: AddressRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val deliveryPricePerKM = 90
    val deliveryAddresses = addressRepository.getAddressesStream()
        .hotFlow(viewModelScope, emptyList())



    private var _currentDeliveryAddress: MutableStateFlow<Address?> = MutableStateFlow(null)
    val currentDeliveryAddress = _currentDeliveryAddress.asStateFlow()


    val cartTotal = cartRepository.getCartTotalStream()
        .hotFlow(viewModelScope, 0)

    @OptIn(ExperimentalCoroutinesApi::class)
    val deliveryFee: StateFlow<Int> = _currentDeliveryAddress
        .mapLatest { address ->
            if (address == null) {
                0
            } else {

                val distance = locationRepository.getLocationDistance(
                    origin = MapCoordinates(- 8.893632, 13.188212),
                    destination = MapCoordinates(address.latitude, address.longitude)
                ) ?: 0.0

                (distance * deliveryPricePerKM).toInt()
            }
        }
        .catch { e ->
            Log.d("repository", "Error calculating deliveryFee: $e")
            emit(0)
        }
        .hotFlow(viewModelScope, 0)


    val orderTotal =
        combine(deliveryFee, cartTotal) { deliveryFee, cartTotal ->
            deliveryFee + cartTotal
        }.hotFlow(viewModelScope, 0)

    init {
        loadInitialAddress()
    }


    fun loadInitialAddress() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _currentDeliveryAddress.value = addressRepository.getLastAddedAddress()
            } catch (e: Exception) {
                Log.e("CheckoutViewModel", "Error fetching delivery addresses: ${e.message}", e)
            }
        }
    }


    fun changeDeliveryAddress(address: Address) {
        _currentDeliveryAddress.value = address
    }


    fun processOrder() {
        viewModelScope.launch(Dispatchers.IO) {
            orderRepository.placeOrder(
                total = orderTotal.value,
                subTotal = cartTotal.value,
                deliveryFee = deliveryFee.value,
                deliveryAddressName = _currentDeliveryAddress.value!!.addressLine.address,
                latitude = _currentDeliveryAddress.value!!.latitude,
                longitude = _currentDeliveryAddress.value!!.longitude,
                paymentType = "",
                cartProductItems = cartRepository.getCartProducts()
            )
                .onSuccess {
                    Log.d("CheckoutViewModel", "processOrder: Success")
                    cartRepository.clearCart()
                }
                .onFailure {
                    Log.d("CheckoutViewModel", "processOrder: Failed ex -> $it")
                }
        }
    }


}

