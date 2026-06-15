package com.example.store.features.authentication.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.store.core.model.resource.isValidEmail
import com.example.store.core.model.resource.isValidName
import com.example.store.core.model.resource.isPasswordValid
import com.example.store.core.model.resource.isValidPhoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(

) : ViewModel() {

    var uiState by mutableStateOf(SignUpUiState())
        private set

    fun updateName(value: String) {
        uiState = uiState.copy(name = value)
    }

    fun updatePhoneNumber(value: String) {
        if (value.length <= 9){
            uiState = uiState.copy(phoneNumber = value)
        }
    }

    fun updateEmail(value: String) {
        uiState = uiState.copy(email = value)
    }

    fun updatePassword(value: String) {
        uiState = uiState.copy(password = value)
    }

    fun updateConfirmPassword(value: String) {
        uiState = uiState.copy(confirmPassword = value)
    }

    fun validateForm() {
        // name
        uiState = uiState.copy(nameError = ! isValidName(uiState.name))

        // email
        uiState = uiState.copy(emailError = ! isValidEmail(uiState.email))

        // phone number
        uiState = uiState.copy(phoneNumberError = ! isValidPhoneNumber(uiState.phoneNumber))

        // password
        uiState = uiState.copy(passwordError = ! isPasswordValid(uiState.password))

        // confirm password
        uiState = uiState.copy(
            confirmPasswordError = uiState.password != uiState.confirmPassword
        )
    }


}

data class SignUpUiState(
    val name: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val location: String = "",

    val nameError: Boolean = false,
    val phoneNumberError: Boolean = false,
    val emailError: Boolean = false,
    val passwordError: Boolean = false,
    val confirmPasswordError: Boolean = false,
    val locationError: Boolean = false,
)