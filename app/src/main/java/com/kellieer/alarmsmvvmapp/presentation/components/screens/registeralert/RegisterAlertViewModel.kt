package com.kellieer.alarmsmvvmapp.presentation.components.screens.registeralert


import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterAlertViewModel : ViewModel() {

    /* ---------- Campos ---------- */
    var title       by mutableStateOf("")
    var description by mutableStateOf("")
    var address     by mutableStateOf("")
    var city        by mutableStateOf("")

    /* ---------- Errores ---------- */
    var titleError       by mutableStateOf("")
    var descriptionError by mutableStateOf("")
    var addressError     by mutableStateOf("")
    var cityError        by mutableStateOf("")

    /* ---------- Validación ---------- */
    val isFormValid: Boolean
        get() = listOf(title, description, address, city).all { it.isNotBlank() }

    fun validateFields() {
        titleError       = if (title.isBlank())       "El título no puede estar vacío"      else ""
        descriptionError = if (description.isBlank()) "La descripción no puede estar vacía" else ""
        addressError     = if (address.isBlank())     "La dirección no puede estar vacía"   else ""
        cityError        = if (city.isBlank())        "La ciudad no puede estar vacía"      else ""
    }

    /**
     * Simula el registro del reporte.
     * Devuelve true si todo está correcto.
     */
    fun attemptRegister(context: Context): Boolean {
        validateFields()
        return if (isFormValid) {
            // TODO: invocar tu repositorio / use-case real.
            Toast.makeText(context, "Reporte registrado exitosamente", Toast.LENGTH_LONG).show()
            true
        } else {
            false
        }
    }
}
