package com.example.store.core.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
fun AttributeDescription(
    modifier: Modifier = Modifier,
    attribute: String,
    value: String,
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontWeight = FontWeight.Light,
                fontSize = MaterialTheme.typography.labelSmall.fontSize
            )
        ) {
            append("$attribute: ")
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Light,
                fontSize = MaterialTheme.typography.labelSmall.fontSize
            )
        ) {
            append(value)
        }
    }
    Text(modifier = Modifier, text = annotatedString)
}