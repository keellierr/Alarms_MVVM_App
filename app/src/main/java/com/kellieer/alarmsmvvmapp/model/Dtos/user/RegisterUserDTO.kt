package com.kellieer.alarmsmvvmapp.model.Dtos.user

data class RegisterUserDTO(
    val name: String,
    val city: String,
    val address: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
    val profileImageUri: String? = null
)