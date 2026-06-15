package com.example.store.features.newaddress.components

import android.content.Context
import android.location.LocationManager
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
internal fun MyLocationButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    IconButton(
        modifier = modifier
            .padding(bottom = 30.dp, end = 16.dp)
            .shadow(10.dp, CircleShape),
        colors = IconButtonDefaults.iconButtonColors(MaterialTheme.colorScheme.secondaryContainer),
        onClick = {
            if (context.isGpsEnabled()) {
                onClick()
            } else {
                Toast.makeText(
                    context,
                    "Active o GPS",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    ) {
        Icon(
            imageVector = Icons.Default.GpsFixed,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

private fun Context.isGpsEnabled(): Boolean {
    val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
}
