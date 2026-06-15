package com.example.store.features.newaddress

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.AddressRepository
import com.example.store.core.data.repository.LocationRepository
import com.example.store.core.model.Address
import com.example.store.core.model.AddressLine
import com.example.store.core.model.AddressType
import com.example.store.core.model.MapCoordinates
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class NewAddressViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val addressRepository: AddressRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewAddressUiState())
    val uiState = _uiState.asStateFlow()

    val searchQuery = mutableStateOf("")


    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchResults = snapshotFlow { searchQuery.value }
        .debounce(800)
        .filter { it.isNotEmpty() }
        .distinctUntilChanged()
        .mapLatest { query ->
            try {
                locationRepository.searchLocation(query)
            } catch (e: Exception) {
                Log.e("DeliveryLocationViewModel", "Error fetching search results: $e", e)
                emptyList()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }

    fun setAddressCoordinates(coordinates: MapCoordinates) {
        _uiState.update { currentState ->
            currentState.copy(
                coordinates = coordinates
            )
        }
    }

    fun updatePhoneNumber(phoneNumber: String) {
        _uiState.update { currentState ->
            currentState.copy(
                phoneNumber = phoneNumber
            )
        }
    }

    fun updateAddressLine(coordinates: MapCoordinates) {
        viewModelScope.launch(Dispatchers.IO) {
            val addressLine = locationRepository.getLocationName(coordinates)
            _uiState.update { currentState ->
                currentState.copy(
                    addressLine = addressLine,
                )
            }
        }
    }

    fun updateReceiverName(name: String) {
        _uiState.update { currentState ->
            currentState.copy(receiverName = name)
        }

    }


    fun updateAddressType(addressType: AddressType) {
        _uiState.update { currentState ->
            currentState.copy(addressType = addressType)
        }
    }

    fun saveAddress() {
        viewModelScope.launch {
            val newAddress = Address(
                id = 0,
                receiverName = _uiState.value.receiverName,
                phoneNumber = _uiState.value.phoneNumber,
                addressType = _uiState.value.addressType,
                addressLine = _uiState.value.addressLine,
                latitude = _uiState.value.coordinates.latitude,
                longitude = _uiState.value.coordinates.longitude,
            )
            addressRepository.addAddress(
                newAddress
            )
        }
    }
}

internal data class NewAddressUiState(
    val receiverName: String = "",
    val phoneNumber: String = "",
    val addressType: AddressType = AddressType.HOME,
    val addressLine: AddressLine = AddressLine("Maianga,Luanda", "56FV+4HV, Luanda"),
    val coordinates: MapCoordinates = MapCoordinates(- 8.8271363, 13.243926)
)
