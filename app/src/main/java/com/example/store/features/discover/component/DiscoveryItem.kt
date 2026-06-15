package com.example.store.features.discover.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.store.features.discover.model.getGenderIcon


@Composable
internal fun DiscoveryItem(
    modifier: Modifier = Modifier,
    gender: String,
    categories: List<String>,
    onCategoryClick: (category: String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val iconRotation by animateFloatAsState(
        targetValue = if (expanded) 0f else - 180f,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = ""
    )

    Column(
        modifier = modifier
            .animateContentSize(
                animationSpec = spring(
                    stiffness = Spring.StiffnessMediumLow,
                    visibilityThreshold = IntSize.VisibilityThreshold
                )

            ),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = ! expanded }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(22.dp),
                    painter = painterResource(getGenderIcon(gender)),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = gender,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Medium
                )
            }
            Icon(
                modifier = Modifier
                    .graphicsLayer {
                        rotationZ = iconRotation
                    },
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = null
            )
        }
        if (expanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 52.dp)
            ) {
                categories.forEachIndexed { index, category ->
                    CategoryItem(
                        category = category,
                        onCategoryClick = { onCategoryClick(category) }
                    )
                    if (index != categories.lastIndex) {
                        HorizontalDivider()
                    }
                }
            }
            HorizontalDivider()
        }
    }
}

@Composable
private fun CategoryItem(
    modifier: Modifier = Modifier,
    category: String,
    onCategoryClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onCategoryClick() }

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(14.dp),
                text = category,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
