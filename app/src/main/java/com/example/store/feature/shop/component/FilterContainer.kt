package com.example.store.feature.shop.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.shop.model.Filter

@Composable
internal fun FilterContainer(
    modifier: Modifier = Modifier,
    currentFilter: String,
    filters: List<Filter>,
    onSelectFilter: (String) -> Unit,
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        filters.forEach { filter ->
            FilterChip(
                enabled = currentFilter != filter.name,
                selected = currentFilter == filter.name,
                onClick = { onSelectFilter(filter.name) },
                label = { Text(text = filter.description) },
                shape = RoundedCornerShape(50),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    labelColor = MaterialTheme.colorScheme.onSecondary,
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    disabledLabelColor = MaterialTheme.colorScheme.onPrimary,
                    disabledSelectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedTrailingIconColor = MaterialTheme.colorScheme.onPrimary,
                    disabledTrailingIconColor = MaterialTheme.colorScheme.onPrimary,
                ),
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = MaterialTheme.colorScheme.background,
                    selectedBorderColor = MaterialTheme.colorScheme.primary,
                    disabledBorderColor = MaterialTheme.colorScheme.primary,

                    selected = currentFilter == filter.name,
                    enabled = true
                ),
                trailingIcon = {
                    if (currentFilter == filter.name) {
                        Icon(
                            modifier = Modifier
                                .size(22.dp)
                                .clip(CircleShape)
                                .clickable { onSelectFilter("") },
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                        )

                    }
                }
            )
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        /* FilterContainer(
             onSelectFilter = {},
             filters = getCategorySectionFilters(CategorySection.Kids)
         )*/
    }
}
