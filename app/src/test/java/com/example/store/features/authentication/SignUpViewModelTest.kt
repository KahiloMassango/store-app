package com.example.store.features.authentication

import com.example.store.features.authentication.signup.SignUpViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SignUpViewModelTest {

    private lateinit var viewModel: SignUpViewModel

    @Before
    fun setup() {
        viewModel = SignUpViewModel()
    }

    @Test
    fun `updateName updates name in uiState`() {
        viewModel.updateName("John Doe")
        assertEquals("John Doe", viewModel.uiState.name)
    }

    @Test
    fun `updatePhoneNumber updates phoneNumber when length is valid`() {
        viewModel.updatePhoneNumber("923456789")
        assertEquals("923456789", viewModel.uiState.phoneNumber)
    }

    @Test
    fun `updatePhoneNumber does not update when length exceeds limit`() {
        viewModel.updatePhoneNumber("9234567890") // 10 dígitos
        assertEquals("", viewModel.uiState.phoneNumber) // Deve permanecer vazio
    }

    @Test
    fun `validateForm sets errors correctly`() {
        viewModel.updateName("")
        viewModel.updateEmail("invalid-email")
        viewModel.updatePhoneNumber("923")
        viewModel.updatePassword("123")
        viewModel.updateConfirmPassword("321")

        viewModel.validateForm()

        assertTrue(viewModel.uiState.nameError)
        assertTrue(viewModel.uiState.emailError)
        assertTrue(viewModel.uiState.phoneNumberError)
        assertTrue(viewModel.uiState.passwordError)
        assertTrue(viewModel.uiState.confirmPasswordError)
    }
}
