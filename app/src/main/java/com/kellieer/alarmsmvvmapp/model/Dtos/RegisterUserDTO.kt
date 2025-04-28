package com.kelliier.alarmsmvvmapp.model.dtos

data class RegisterUserDTO(
    val name: String,
    val city: String,
    val address: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
