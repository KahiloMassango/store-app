package com.example.store.features.userprofile.helpcenter.faq.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun QuestionCategorySelector(
    modifier: Modifier = Modifier,
    currentCategory: QuestionCategory,
    onCategoryClick: (QuestionCategory) -> Unit
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        QuestionCategory.entries.forEach { category ->
            val selected = currentCategory == category


            val backgroundColor = if (selected) MaterialTheme.colorScheme.secondary else
                Color.Transparent
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(backgroundColor, RoundedCornerShape(50))
                    .clickable { onCategoryClick(category) }
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = category.description,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    color = if (selected) MaterialTheme.colorScheme.onSecondary
                    else MaterialTheme.colorScheme.onSurface,
                )

            }
        }
    }
}