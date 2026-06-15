package com.example.store.features.userprofile.editprofile

import android.net.Uri
import android.util.Log
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
internal class EditProfileViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    var userPhoto: Uri? by mutableStateOf(null)
        private set

    var username by mutableStateOf("")
        private set

    var userEmail by mutableStateOf("")
        private set

    var userPhoneNumber by mutableStateOf("")
        private set


    fun updateUsername(username: String) {
        this.username = username
    }

    fun updateEmail(email: String) {
        userEmail = email
    }

    fun updatePhoneNumber(phoneNumber: String) {
        userPhoneNumber = phoneNumber
    }

    fun setPhoto(newUri: Uri) {
        userPhoto = newUri

    }

    init {
        getUserDetails()
    }

    fun getUserDetails() {
        viewModelScope.launch {
            val user = accountRepository.getLocalUserDetails()
            username = user.username
            userEmail = user.email
            userPhoneNumber = user.phoneNumber
        }
    }


    fun saveProfile() {
        viewModelScope.launch {
            accountRepository.updateUser(
                username = username,
                email = userEmail,
                phoneNumber = userPhoneNumber,
            )
                .onSuccess {
                    username = it.username
                    userEmail = it.email
                    userPhoneNumber = it.phoneNumber
                }
                .onFailure {
                    Log.d("EditProfileViewModel", "saveProfile: ${it.message}")
                }
        }
    }


}