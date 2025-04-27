package com.kellieer.alarmsmvvmapp.presentation.components.screens.login

import androidx.compose.runtime.mutableStateOf
import android.util.Patterns
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kellieer.alarmsmvvmapp.controller.UserSessionManager

class LoginViewModel  : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var emailError by mutableStateOf("")
    var passwordError by mutableStateOf("")

    val isFormValid: Boolean
        get() = email.isNotBlank() && password.length >= 6 && email.contains("@")

    fun validateFields() {
        emailError = if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) "Email no válido" else ""
        passwordError = if (password.length < 6) "La contraseña debe tener al menos 6 caracteres" else ""
    }

    fun attemptLogin(context: Context): String? {
        return when {
            email == "admin@gmail.com" && password == "admin123" -> {
                UserSessionManager.guardarRol(context, "admin")
                "admin"
            }
            email == "user@gmail.com" && password == "user123" -> {
                UserSessionManager.guardarRol(context, "usuario")
                "usuario"
            }
            else -> null
        }
    }


}