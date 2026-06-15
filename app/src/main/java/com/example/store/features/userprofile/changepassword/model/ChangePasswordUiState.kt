package com.example.store.features.userprofile.changepassword.model

internal data class ChangePasswordUiState(
    val newPassword: String = "",
    val repeatPassword: String = ""
)