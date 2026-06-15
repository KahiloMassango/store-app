package com.example.store.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.theme.StoreTheme

@Composable
fun StoreTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    elevation: Dp = 2.dp,
    singleLine: Boolean = true,
    isError: Boolean = false,
    enabled: Boolean = true,
    supportingText: String? = null,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .shadow(
                    elevation,
                    MaterialTheme.shapes.small
                ),
            value = value,
            textStyle = MaterialTheme.typography.bodyMedium,
            isError = isError,
            leadingIcon = leadingIcon,
            singleLine = singleLine,
            enabled = enabled,
            onValueChange = { onValueChange(it) },
            trailingIcon = trailingIcon,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorSupportingTextColor = MaterialTheme.colorScheme.error,
                errorIndicatorColor = Color.Unspecified,
                errorContainerColor = MaterialTheme.colorScheme.background
            ),
            label = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        )
        if (supportingText != null) {
            Text(
                modifier = Modifier.padding(start = 12.dp),
                text = supportingText,
                style = MaterialTheme.typography.bodySmall,
                color = if (isError) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.inverseOnSurface
            )
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        var value by remember {
            mutableStateOf("")
        }
        StoreTextField(
            value = value,
            onValueChange = { value = it },
            placeholder = "Password",
            isError = false,
        )
    }
}