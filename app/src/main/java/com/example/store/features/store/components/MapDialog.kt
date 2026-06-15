package com.example.store.features.store.components

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.core.model.Address
import com.example.store.core.model.AddressLine
import com.example.store.core.model.AddressType
import com.example.store.core.model.store.Store
import com.example.store.core.ui.theme.StoreTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import java.time.LocalTime

@Composable
internal fun StoreMapDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    store: Store
) {
    val mapState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(store.latitude, store.longitude), 18f
        )
    }

    var isMapLoaded by rememberSaveable { mutableStateOf(true) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.3f))
            .clickable(indication = null, interactionSource = null) { onDismiss() },
        contentAlignment = Alignment.Center
    ) {
        BackHandler {
            onDismiss()
        }
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .clip(RoundedCornerShape(5))
        ) {
            GoogleMap(
                modifier = Modifier
                    .align(Alignment.Center),
                cameraPositionState = mapState,
                onMapLoaded = { isMapLoaded = true },
                uiSettings = MapUiSettings(zoomControlsEnabled = false, zoomGesturesEnabled = false)
            ) {
                Marker(state = rememberMarkerState(position = mapState.position.target))
            }
            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = onDismiss
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outlineVariant
                )
            }
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.BottomCenter),
                enter = slideInVertically { it },
                visible = isMapLoaded
            ) {
                StoreAddressInfoCard(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    address = store.address,
                    latitude = store.latitude,
                    longitude = store.longitude,
                    storeName = store.name,
                    openingTime = store.openingTime,
                    closingTime = store.closingTime,
                )
            }
        }
    }
}

@Composable
private fun StoreAddressInfoCard(
    modifier: Modifier = Modifier,
    address: String,
    storeName: String,
    latitude: Double,
    longitude: Double,
    openingTime: String,
    closingTime: String
) {
    val context = LocalContext.current
    val gmmIntentUri = Uri.parse("google.navigation:q=${latitude},${longitude}")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    //mapIntent.setPackage("com.google.android.apps.maps")

    val isOpen = LocalTime.parse(openingTime) <= LocalTime.now()
            && LocalTime.now() <= LocalTime.parse(closingTime)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.secondaryContainer,
                RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = storeName,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "$openingTime - $closingTime",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    overflow = TextOverflow.Ellipsis
                )
                Box(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.outlineVariant,
                            RoundedCornerShape(50)
                        )
                        .size(5.dp)
                        .clip(RoundedCornerShape(50))
                )
                Text(
                    text = if (isOpen) "Aberto" else "Fechado",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isOpen) MaterialTheme.colorScheme.scrim else MaterialTheme.colorScheme.onError,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = address,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            GetDirectionButton(onClick = { context.startActivity(mapIntent) })
        }
    }
}

@Composable
private fun GetDirectionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                modifier = Modifier.size(26.dp),
                imageVector = Icons.Default.Directions,
                contentDescription = null,
                tint = Color.White
            )
            Text(
                text = "Direções",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val address = Address(
        1,
        "test1",
        "123456789",
        AddressType.HOME,
        AddressLine("Samba, Luanda", "street 1, Gamek, Luanda"),
        -8.889142, 13.201371,
    )
    StoreTheme {
        StoreAddressInfoCard(
            address = address.addressLine.shortName,
            latitude = address.latitude,
            longitude = address.longitude,
            storeName = "TODO()",
            openingTime = "TODO()",
            closingTime = "TODO()",
        )
    }
}
