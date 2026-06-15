package com.example.store.features.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.store.core.model.search.RecentSearch
import kotlin.collections.forEach

@Composable
internal fun RecentSearchQueries(
    modifier: Modifier = Modifier,
    recentSearchList: List<RecentSearch> = emptyList(),
    onRecentSearchClick: (String) -> Unit,
    onClearAllRecentSearch: () -> Unit,
    onDeleteRecentSearch: (Int) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Pesquisas Recentes",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                modifier = Modifier
                    .clickable(
                        interactionSource = null,
                        indication = null,
                        onClick = onClearAllRecentSearch
                    ),
                text = "Limpar Tudo",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(Modifier.height(16.dp))
        recentSearchList.forEach { recentSearch ->
            RecentSearchItem(
                modifier = Modifier.fillMaxWidth(),
                recentSearch = recentSearch,
                onClick = { onRecentSearchClick(it) },
                onDelete = { id -> onDeleteRecentSearch(id) }
            )
        }
    }
}
