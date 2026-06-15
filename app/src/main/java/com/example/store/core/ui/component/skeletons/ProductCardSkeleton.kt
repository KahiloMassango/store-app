package com.example.store.core.ui.component.skeletons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.ShimmerBox

@Composable
fun ProductCardSkeleton(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.width(164.dp),
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            ShimmerBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(184.dp),
                cornerRadius = 10
            )
            ShimmerBox(
                Modifier
                    .fillMaxWidth(0.9f)
                    .height(12.dp),
                cornerRadius = 20
            )
            ShimmerBox(
                Modifier
                    .fillMaxWidth(0.6f)
                    .height(12.dp),
                cornerRadius = 20
            )
            ShimmerBox(
                Modifier
                    .fillMaxWidth(0.4f)
                    .height(12.dp),
                cornerRadius = 20
            )
        }
    }
}
