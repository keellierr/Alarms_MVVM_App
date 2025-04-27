package com.kellieer.alarmsmvvmapp.presentation.components.screens.register

import android.util.Patterns
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    var nombre by mutableStateOf("")
    var ciudad by mutableStateOf("")
    var direccion by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmarPassword by mutableStateOf("")

    var ciudadError by mutableStateOf("")
    var direccionError by mutableStateOf("")
    var emailError by mutableStateOf("")
    var passwordError by mutableStateOf("")
    var confirmarPasswordError by mutableStateOf("")

    val isButtonEnabled: Boolean
        get() = nombre.isNotBlank() &&
                ciudadError.isEmpty() &&
                direccionError.isEmpty() &&
                emailError.isEmpty() &&
                passwordError.isEmpty() &&
                confirmarPasswordError.isEmpty()

    fun validateFields() {
        ciudadError = if (ciudad.isEmpty()) "La ciudad no puede estar vacía" else ""
        direccionError = when {
            direccion.isBlank() -> "La dirección no puede estar vacía"
            !direccion.contains(Regex("[#_\\-/]")) ->
                "La dirección debe contener al menos uno de los siguientes caracteres: #, _, -, /"
            else -> ""
        }
        emailError = if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) "El correo no es válido" else ""
        passwordError = if (password.length < 6) "Debe tener al menos 6 caracteres" else ""
        confirmarPasswordError = if (confirmarPassword != password) "Las contraseñas no coinciden" else ""
    }
}
