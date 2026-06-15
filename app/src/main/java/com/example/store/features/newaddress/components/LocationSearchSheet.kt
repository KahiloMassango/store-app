package com.example.store.features.newaddress.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.store.core.model.Location
import com.example.store.core.ui.component.SearchField
import com.google.android.gms.maps.model.LatLng

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LocationSearchSheet(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onUseUserCurrentLocation: () -> Unit,
    onSelectLocation: (LatLng) -> Unit,
    searchResult: List<Location>,
    onDismissRequest: () -> Unit
) {
    val keyboardManager = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    var textFieldLoaded by remember { mutableStateOf(false) }


    ModalBottomSheet(
        modifier = modifier,
        sheetState = rememberModalBottomSheetState(false),
        containerColor = MaterialTheme.colorScheme.surface.copy(0.99f),
        scrimColor = Color(0xFF000000).copy(0.3f),
        contentColor = MaterialTheme.colorScheme.onSurface,
        onDismissRequest = onDismissRequest,
        contentWindowInsets = { WindowInsets.ime.union(WindowInsets.navigationBars) }
    ) {
        Column(
            modifier = Modifier
        ) {
            SearchField(
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .focusRequester(focusRequester)
                    .onGloballyPositioned {
                        if (! textFieldLoaded) {
                            focusRequester.requestFocus() // IMPORTANT
                            textFieldLoaded = true
                        }
                    },
                query = query,
                placeholder = "Pesquise por locais",
                onQueryChange = { onQueryChange(it) },
                onClearQuery = { onQueryChange("") },
                onSearch = { keyboardManager?.hide() }
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                modifier = Modifier,
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {
                    /*UseCurrentLocationButton(
                        onClick = onUseUserCurrentLocation
                    )*/
                }
                items(searchResult) { location ->
                    LocationItem(
                        locationName = location.addressLine,
                        onClick = {
                            onSelectLocation(
                                LatLng(location.latitude, location.longitude)
                            )
                        }
                    )
                }
            }
        }
    }
}


@Composable
private fun LocationItem(
    modifier: Modifier = Modifier,
    locationName: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
    ) {
        HorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(4.dp),
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = locationName,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .padding(4.dp),
                imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.inverseOnSurface
            )
        }
    }
}
