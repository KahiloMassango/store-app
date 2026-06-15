package com.example.store.features.authentication.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.theme.StoreTheme

@Composable
fun CustomClickableText(
    modifier: Modifier = Modifier,
    text: String,
    supportText: String? = null,
    onClick: () -> Unit,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center
) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)
        ){
            append(text)
        }
        append(" ")
        if(supportText != null) {
            withStyle(
                style = SpanStyle(color = MaterialTheme.colorScheme.primary)
            ) {
                append(supportText)
            }
        }
    }

    Row(
        modifier = modifier,
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement
    ) {
        BasicText(
            text = annotatedString,
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(end = 4.dp),
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    StoreTheme {
        CustomClickableText(
            text = "Forgot your password?",
            onClick = {  }
        )
    }
}