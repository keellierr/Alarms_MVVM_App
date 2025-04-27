package com.kellieer.alarmsmvvmapp.presentation.components.screens.editalert


import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class EditAlertViewModel : ViewModel() {

    var title        by mutableStateOf("")
    var description  by mutableStateOf("")
    var address      by mutableStateOf("")
    var city         by mutableStateOf("")

    var titleError       by mutableStateOf("")
    var descriptionError by mutableStateOf("")
    var addressError     by mutableStateOf("")
    var cityError        by mutableStateOf("")

    val isFormValid: Boolean
        get() = listOf(title, description, address, city).all { it.isNotBlank() }

    fun validateFields() {
        titleError       = if (title.isBlank())       "El título no puede estar vacío"          else ""
        descriptionError = if (description.isBlank()) "La descripción no puede estar vacía"     else ""
        addressError     = if (address.isBlank())     "La dirección no puede estar vacía"       else ""
        cityError        = if (city.isBlank())        "La ciudad no puede estar vacía"          else ""
    }


    fun attemptUpdate(context: Context): Boolean {
        validateFields()
        return if (isFormValid) {

            Toast.makeText(context, "Evento actualizado exitosamente", Toast.LENGTH_LONG).show()
            true
        } else {
            false
        }
    }
}

