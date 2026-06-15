package com.example.store.features.store.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun StoreSectionTabRow(
    modifier: Modifier = Modifier,
    currentSection: StoreSection,
    onChangeSection: (StoreSection) -> Unit
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = currentSection.ordinal,
        divider = {}
    ) {
        StoreSection.entries.forEachIndexed { index, section ->
            Tab(
                modifier = Modifier,
                selected = index == currentSection.ordinal,
                onClick = { onChangeSection(section) }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 14.dp),
                    text = section.description,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (index == currentSection.ordinal) MaterialTheme.colorScheme.onSurface
                    else MaterialTheme.colorScheme.outlineVariant
                )
            }
        }
    }
}
