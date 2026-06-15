package com.example.store.features.userprofile.changepassword

import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.store.core.ui.component.CustomButton
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.core.ui.component.StoreTextField
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChangePasswordScreen(
    modifier: Modifier = Modifier,
    viewModel: ChangePasswordViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val keyboardManager = LocalSoftwareKeyboardController.current

    val uiState = viewModel.uiState.value

    var showPassword by remember { mutableStateOf(false) }
    var showRepeatPassword by remember { mutableStateOf(false) }

    val passwordMatches by remember {
        derivedStateOf {
            uiState.newPassword == uiState.repeatPassword
        }
    }

    val context = LocalContext.current


    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            StoreLargeTopBar(
                title = "Alterar senha",
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
            )
        },
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            focusManager.clearFocus()
                            keyboardManager?.hide()
                        }
                    )
                }
        ) {
            Column(
                modifier = Modifier
                    //.fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StoreTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.newPassword,
                    placeholder = "Nova senha",
                    supportingText = "(Mínimo 8 caracteres)",
                    onValueChange = { viewModel.updateNewPassword(it) },
                    visualTransformation = if (showPassword) VisualTransformation.None
                        else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.moveFocus(FocusDirection.Next) }
                    ),
                    trailingIcon = {
                        IconButton(
                            onClick = { showPassword = ! showPassword }
                        ) {
                            Icon(
                                imageVector = if (showPassword) Icons.Default.VisibilityOff
                                else Icons.Default.Visibility,
                                contentDescription = null
                            )
                        }
                    }
                )
                StoreTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.repeatPassword,
                    placeholder = "Confirme nova senha",
                    isError = ! passwordMatches,
                    onValueChange = { viewModel.updateRepeatPassword(it) },
                    visualTransformation = if (showRepeatPassword) VisualTransformation.None
                        else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus(true) }
                    ),
                    trailingIcon = {
                        IconButton(
                            onClick = { showRepeatPassword = ! showRepeatPassword }
                        ) {
                            Icon(
                                imageVector = if (showRepeatPassword) Icons.Default.VisibilityOff
                                else Icons.Default.Visibility,
                                contentDescription = null
                            )
                        }
                    }
                )

                CustomButton(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = "Salvar",
                    onClick = {
                        when {
                            uiState.newPassword.length < 8 -> {
                                Toast.makeText(
                                    context,
                                    "A senha deve ter no mínimo 8 caracteres",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            uiState.newPassword != uiState.repeatPassword -> {
                                Toast.makeText(
                                    context,
                                    "As senhas não coincidem",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                )
            }
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        ChangePasswordScreen {}
    }
}