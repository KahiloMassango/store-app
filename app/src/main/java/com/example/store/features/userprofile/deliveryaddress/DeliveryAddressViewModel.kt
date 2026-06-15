package com.example.store.features.userprofile.deliveryaddress

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.AddressRepository
import com.example.store.core.ui.util.hotFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeliveryAddressesViewModel @Inject constructor(
    private val addressRepository: AddressRepository,
) : ViewModel() {

    val deliveryAddresses = addressRepository.getAddressesStream()
        .hotFlow(viewModelScope, initialValue = emptyList())


    fun deleteAddress(addressId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                addressRepository.deleteAddressById(addressId)
            } catch (e: Exception) {
                Log.d("DeliveryAddressesViewModel", "deleteAddress: $e")
            }
        }
    }

}

