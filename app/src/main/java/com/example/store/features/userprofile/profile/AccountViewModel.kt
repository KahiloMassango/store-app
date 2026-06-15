package com.example.store.features.userprofile.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    var username by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            val user = accountRepository.getLocalUserDetails()
            username = user.username
            email = user.email
        }
    }

    fun logout() {
        viewModelScope.launch {
            accountRepository.logout()
        }
    }
}

