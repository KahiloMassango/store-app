package com.example.store.features.userprofile.changepassword

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.store.features.userprofile.changepassword.model.ChangePasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
internal class ChangePasswordViewModel @Inject constructor(
) : ViewModel() {

    val uiState = mutableStateOf(ChangePasswordUiState())

    fun updateNewPassword(newPassword: String) {
        uiState.value = uiState.value.copy(newPassword = newPassword)
    }

    fun updateRepeatPassword(repeatPassword: String) {
        uiState.value = uiState.value.copy(repeatPassword = repeatPassword)
    }

}

