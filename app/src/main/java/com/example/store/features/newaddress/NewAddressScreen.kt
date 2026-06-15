package com.example.store.features.newaddress

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.model.AddressLine
import com.example.store.core.model.AddressType
import com.example.store.core.model.Location
import com.example.store.core.model.MapCoordinates
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.features.newaddress.components.AddressDetailsSheet
import com.example.store.features.newaddress.components.AddressInformationCard
import com.example.store.features.newaddress.components.CustomMarker
import com.example.store.features.newaddress.components.LocationPermissionScreen
import com.example.store.features.newaddress.components.LocationSearchSheet
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.ComposeMapColorScheme
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@Composable
internal fun NewAddressScreen(
    modifier: Modifier = Modifier,
    viewModel: NewAddressViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit
) {
    val context = LocalContext.current
    var hasLocationPermission by remember {
        mutableStateOf(
            context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED
        )
    }
    val requestLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            hasLocationPermission = true
        } else {
            onNavigateUp()
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()

    if (hasLocationPermission) {
        NewAddressContent(
            modifier = modifier,
            coordinates = uiState.coordinates,
            addressLine = uiState.addressLine,
            query = searchQuery,
            searchResult = searchResults,
            receiverName = uiState.receiverName,
            phoneNumber = uiState.phoneNumber,
            onReceiverNameChange = { viewModel.updateReceiverName(it) },
            onPhoneNumberChange = { viewModel.updatePhoneNumber(it) },
            selectedAddressType = uiState.addressType,
            onChooseAddressType = { viewModel.updateAddressType(it) },
            onQueryChange = { viewModel.updateSearchQuery(it) },
            onLocationChange = { viewModel.updateAddressLine(it) },
            onNavigateUp = onNavigateUp,
            onConfirmLocation = { coordinates ->
                viewModel.setAddressCoordinates(coordinates)
            },
            onSaveAddress = {
                viewModel.saveAddress()
                onNavigateUp()
            }
        )
    } else {
        LocationPermissionScreen(
            modifier = modifier,
            onGrant = {
                requestLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        )
    }


}

@Composable
private fun NewAddressContent(
    modifier: Modifier = Modifier,
    coordinates: MapCoordinates,
    addressLine: AddressLine,
    query: String,
    searchResult: List<Location>,
    receiverName: String,
    phoneNumber: String,
    onReceiverNameChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    selectedAddressType: AddressType,
    onChooseAddressType: (AddressType) -> Unit,
    onSaveAddress: () -> Unit,
    onQueryChange: (String) -> Unit,
    onLocationChange: (MapCoordinates) -> Unit,
    onConfirmLocation: (MapCoordinates) -> Unit,
    onNavigateUp: () -> Unit
) {
    var isSearching by rememberSaveable { mutableStateOf(false) }
    var isAddingDetails by rememberSaveable { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(coordinates.latitude, coordinates.longitude), 17.2f
        )
    }

    // Run when user change location in map
    LaunchedEffect(cameraPositionState.isMoving) {
        if (!cameraPositionState.isMoving) {
            onLocationChange(
                MapCoordinates(
                    cameraPositionState.position.target.latitude,
                    cameraPositionState.position.target.longitude
                )
            )
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            StoreCenteredTopBar(
                title = "Adicionar endereço",
                canNavigateBack = true,
                onNavigateUp = onNavigateUp,
                action = {
                    IconButton(onClick = { isSearching = true }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    properties = MapProperties(
                        isMyLocationEnabled = true,
                    ),
                    uiSettings = MapUiSettings(
                        zoomControlsEnabled = false,
                        myLocationButtonEnabled = true
                    ),
                    mapColorScheme = ComposeMapColorScheme.FOLLOW_SYSTEM
                )
                CustomMarker(
                    Modifier
                        .align(Alignment.Center)
                        .offset(y = -(15).dp)
                )
            }
            AddressInformationCard(
                modifier = Modifier.align(Alignment.BottomCenter),
                addressLine = addressLine,
                onConfirmLocation = {
                    onConfirmLocation(
                        MapCoordinates(
                            cameraPositionState.position.target.latitude,
                            cameraPositionState.position.target.longitude
                        )
                    )
                    isAddingDetails = true
                }
            )
        }

        if (isSearching) {
            LocationSearchSheet(
                modifier = Modifier,
                query = query,
                onQueryChange = { onQueryChange(it) },
                searchResult = searchResult,
                onSelectLocation = { coordinates ->
                    coroutineScope.launch {
                        cameraPositionState.animate(
                            CameraUpdateFactory.newLatLng(
                                LatLng(
                                    coordinates.latitude,
                                    coordinates.longitude
                                )
                            )
                        )
                    }
                    isSearching = false
                },
                onUseUserCurrentLocation = {
                    isSearching = false
                },
                onDismissRequest = { isSearching = false },
            )
        }

        if (isAddingDetails) {
            AddressDetailsSheet(
                modifier = Modifier,
                receiverName = receiverName,
                phoneNumber = phoneNumber,
                onReceiverNameChange = onReceiverNameChange,
                onPhoneNumberChange = onPhoneNumberChange,
                selectedAddressType = selectedAddressType,
                onChooseAddressType = onChooseAddressType,
                onDismissRequest = { isAddingDetails = false },
                saveAddress = onSaveAddress,
            )
        }
    }
}

