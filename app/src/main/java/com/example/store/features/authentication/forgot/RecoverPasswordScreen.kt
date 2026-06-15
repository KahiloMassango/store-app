package com.example.store.features.authentication.forgot

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.CustomButton
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.core.ui.component.StoreTextField
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme

@Composable
fun RecoverPasswordScreen(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var phoneNumber by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier,
        topBar = {
            StoreLargeTopBar(
                title = "Recuperar senha",
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { focusManager.clearFocus() }
                    )
                }
        ){
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
            ) {
                Spacer(Modifier.height(56.dp))
                Text(
                    text = "Por favor, indique o seu número de telefone. " +
                        "Para recuperarmos sua senha.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,

                )
                Spacer(modifier = Modifier.height(22.dp))
                StoreTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = phoneNumber,
                    placeholder = "Telefone",
                    onValueChange = { phoneNumber = it },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Phone
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Prosseguir",
                    onClick = { /* TODO */ }
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        RecoverPasswordScreen{}
    }
}