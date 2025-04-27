package com.kellieer.alarmsmvvmapp.presentation.components.screens.recoverpassword

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RecoverPasswordViewModel : ViewModel() {

    /* ------------ Campos ------------ */
    var email            by mutableStateOf("")
    var currentPassword  by mutableStateOf("")
    var newPassword      by mutableStateOf("")
    var passwordConfirm  by mutableStateOf("")

    /* ------------ Errores ------------ */
    var emailError            by mutableStateOf("")
    var currentPasswordError  by mutableStateOf("")
    var newPasswordError      by mutableStateOf("")
    var passwordConfirmError  by mutableStateOf("")

    /* ------------ Validación global ------------ */
    val isFormValid: Boolean
        get() = listOf(
            emailError,
            currentPasswordError,
            newPasswordError,
            passwordConfirmError
        ).all { it.isEmpty() } &&
                listOf(
                    email,
                    currentPassword,
                    newPassword,
                    passwordConfirm
                ).all { it.isNotBlank() }

    fun validateEmail() {
        emailError =
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                "El correo no es válido"
            else ""
    }

    fun validateCurrentPassword() {
        currentPasswordError =
            if (currentPassword.isBlank())
                "La contraseña actual es obligatoria"
            else ""
    }

    fun validateNewPassword() {
        newPasswordError =
            if (newPassword.length < 6)
                "La nueva contraseña debe tener al menos 6 caracteres"
            else ""
    }

    fun validatePasswordConfirm() {
        passwordConfirmError =
            if (passwordConfirm != newPassword)
                "Las contraseñas no coinciden"
            else ""
    }

    /**
     * Simula el cambio de contraseña en tu backend.
     * Devuelve **true** si todo fue correcto.
     */
    fun attemptChange(context: Context): Boolean {
        validateEmail()
        validateCurrentPassword()
        validateNewPassword()
        validatePasswordConfirm()

        if (!isFormValid) return false

        // TODO: Llamar a tu repositorio / use-case real
        Toast.makeText(context, "Contraseña cambiada correctamente", Toast.LENGTH_LONG).show()
        return true
    }
}
