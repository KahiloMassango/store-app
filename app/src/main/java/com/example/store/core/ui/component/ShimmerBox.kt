package com.example.store.core.ui.component


import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

@Composable
fun ShimmerBox(
    modifier: Modifier,
    cornerRadius: Int = 12,
) {
    val durationMillis = 1600

    val transition = rememberInfiniteTransition(label = "")
    val lightColor = MaterialTheme.colorScheme.outlineVariant.copy(0.5f)
    val darkColor = MaterialTheme.colorScheme.outlineVariant
    val translateAnimation = transition.animateColor(
        initialValue = darkColor,
        targetValue = lightColor,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Reverse,
        ), label = ""
    )
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(translateAnimation.value, RoundedCornerShape(cornerRadius))
    )
}