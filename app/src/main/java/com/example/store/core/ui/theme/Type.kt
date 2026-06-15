package com.example.store.core.ui.theme


import com.example.store.R
import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

// Set of Material typography styles to start with
val defaultFont = FontFamily(
    Font(R.font.metropolis_bold, FontWeight.Bold),
    Font(R.font.metropolis_medium, FontWeight.Medium),
    Font(R.font.metropolis_regular, FontWeight.Normal),
    Font(R.font.metropolis_semi_bold, FontWeight.SemiBold)
)
val baseline = Typography()
val Typography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = defaultFont),
    displayMedium = baseline.displayMedium.copy(fontFamily = defaultFont),
    displaySmall = baseline.displaySmall.copy(fontFamily = defaultFont),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = defaultFont),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = defaultFont),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = defaultFont),
    titleLarge = baseline.titleLarge.copy(fontFamily = defaultFont),
    titleMedium = baseline.titleMedium.copy(fontFamily = defaultFont),
    titleSmall = baseline.titleSmall.copy(fontFamily = defaultFont),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = defaultFont),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = defaultFont),
    bodySmall = baseline.bodySmall.copy(fontFamily = defaultFont),
    labelLarge = baseline.labelLarge.copy(fontFamily = defaultFont),
    labelMedium = baseline.labelMedium.copy(fontFamily = defaultFont),
    labelSmall = baseline.labelSmall.copy(fontFamily = defaultFont),

    )