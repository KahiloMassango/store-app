package com.example.store.features.userprofile.helpcenter.faq.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


private enum class HelpCenterSection(val description: String) {
    FAQ("FAQ"),
    CONTACTUS("Contate-nos")
}



@Composable
internal fun HelpCenterTabRow(
    modifier: Modifier = Modifier,
    selectedTab: Int,
    onTabClick: (Int) -> Unit,
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTab,
        // containerColor = MaterialTheme.colorScheme.inverseOnSurface.copy(0.1f),
        contentColor = MaterialTheme.colorScheme.onBackground,
        // divider = {}
    ) {
        HelpCenterSection.entries.forEach { section ->
            val selected = section.ordinal == selectedTab
            Tab(
                selected = section.ordinal == selectedTab,
                onClick = { onTabClick(section.ordinal) }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 14.dp),
                    text = section.description,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight =  FontWeight.SemiBold,
                    color = if(selected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.inverseOnSurface
                )
            }
        }
    }
}