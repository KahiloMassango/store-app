package com.example.store.features.authentication.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    var isLoggedIn by mutableStateOf(false)
    var message = MutableStateFlow<String?>(null)
        private set

    fun login(email: String, password: String) {
        if (email.isBlank() && password.isBlank()) {
            message.value = "Por favor, preencha todos os campos"
            return
        }

        viewModelScope.launch {
            accountRepository.login(email, password)
                .onSuccess {
                    isLoggedIn = true
                }
                .onFailure { e ->
                    message.value = e.message ?: "Credenciais inválidas"
                }
        }
    }

    fun messageShown() {
        message.value = null
    }

}