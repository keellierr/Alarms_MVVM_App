package com.kellieer.alarmsmvvmapp.presentation.components.screens.editregister

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class EditRegisterViewModel : ViewModel() {

    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var address by mutableStateOf("")
    var birthDate by mutableStateOf("")
    var profileImageUri by mutableStateOf<String?>(null)

    var errorName by mutableStateOf("")
    var errorEmail by mutableStateOf("")
    var errorUsername by mutableStateOf("")
    var errorPassword by mutableStateOf("")
    var errorPhoneNumber by mutableStateOf("")
    var errorAddress by mutableStateOf("")
    var errorBirthDate by mutableStateOf("")

    val isButtonEnabled: Boolean
        get() = name.isNotBlank() &&
                email.isNotBlank() &&
                username.isNotBlank() &&
                password.isNotBlank() &&
                phoneNumber.isNotBlank() &&
                address.isNotBlank() &&
                birthDate.isNotBlank()

    fun validate() {
        errorName = if (name.isEmpty()) "El nombre no puede estar vacío" else ""
        errorEmail = if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) "El correo no es válido" else ""
        errorUsername = if (username.length < 5) "Debe tener al menos 5 caracteres" else ""
        errorPassword = if (password.length < 6) "Debe tener al menos 6 caracteres" else ""
        errorPhoneNumber = if (phoneNumber.isEmpty()) "El número no puede estar vacío" else ""
        errorAddress = if (address.isEmpty() || !address.contains(Regex("[#_\\-/]"))) {
            "Debe contener caracteres especiales como #, _, -, /"
        } else ""
        errorBirthDate = if (birthDate.isEmpty()) "La fecha de nacimiento no puede estar vacía" else ""
    }
}
