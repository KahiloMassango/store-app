package com.example.store.core.model.resource

import android.util.Patterns
import androidx.core.text.isDigitsOnly

fun isPasswordValid(password: String): Boolean {
    return password.length >= 8
}


fun isValidName(name: String): Boolean {
    val regex = Regex("^[a-zA-Z ]*\$")
    return name.length >= 4 && regex.matches(name)
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    return email.matches(emailRegex)
}

fun isValidPhoneNumber(phoneNumber: String): Boolean {
    val n = phoneNumber.length
    return n == 9 && phoneNumber.isDigitsOnly()
}
