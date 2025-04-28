package com.kellieer.alarmsmvvmapp.presentation.components.screens.register

import android.util.Patterns
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    var name by mutableStateOf("")
    var city by mutableStateOf("")
    var address by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    var cityError by mutableStateOf("")
    var addressError by mutableStateOf("")
    var emailError by mutableStateOf("")
    var passwordError by mutableStateOf("")
    var confirmPasswordError by mutableStateOf("")

    val isButtonEnabled: Boolean
        get() = name.isNotBlank() &&
                cityError.isEmpty() &&
                addressError.isEmpty() &&
                emailError.isEmpty() &&
                passwordError.isEmpty() &&
                confirmPasswordError.isEmpty()

    fun validateFields() {
        cityError = if (city.isEmpty()) "La ciudad no puede estar vacía" else ""
        addressError = when {
            address.isBlank() -> "La dirección no puede estar vacía"
            !address.contains(Regex("[#_\\-/]")) ->
                "La dirección debe contener al menos uno de los siguientes caracteres: #, _, -, /"
            else -> ""
        }
        emailError = if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) "El correo no es válido" else ""
        passwordError = if (password.length < 6) "La contraseña debe tener al menos 6 caracteres" else ""
        confirmPasswordError = if (confirmPassword != password) "Las contraseñas no coinciden" else ""
    }
}