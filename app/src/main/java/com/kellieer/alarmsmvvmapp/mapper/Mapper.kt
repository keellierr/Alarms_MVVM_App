package com.kellieer.alarmsmvvmapp.mapper

import com.kellieer.alarmsmvvmapp.model.Alert
import com.kellieer.alarmsmvvmapp.model.Dtos.alert.RegisterAlertDTO
import com.kellieer.alarmsmvvmapp.model.Dtos.user.EditRegisterUserDTO
import com.kellieer.alarmsmvvmapp.model.Role
import com.kellieer.alarmsmvvmapp.model.User
import com.kellieer.alarmsmvvmapp.presentation.components.screens.register.RegisterViewModel
import com.kellieer.alarmsmvvmapp.model.Dtos.user.RegisterUserDTO
import com.kellieer.alarmsmvvmapp.presentation.components.screens.editregister.EditRegisterViewModel
import com.kelliier.alarmsmvvmapp.presentation.components.screens.registeralert.RegisterAlertViewModel
import java.util.UUID

object Mapper {
    fun fromRegisterUserDTO(dto: RegisterUserDTO): User {
        return User(
            id = UUID.randomUUID().toString(),
            city = dto.city,
            address = dto.address,
            name = dto.name,
            email = dto.email,
            password = dto.password,
            role = Role.USER,
            alerts = emptyList(),
            profileImageUri = dto.profileImageUri
        )
    }

    fun fromRegisterAlertDTO(dto: RegisterAlertDTO): Alert {
        return Alert(
            id = UUID.randomUUID().toString(),
            title = dto.title,
            category = dto.category,
            description = dto.description,
            address = dto.address,
            latitude = dto.latitude,
            longitude = dto.longitude,
            imageUri = dto.imageUri
        )
    }

    fun toRegisterUserDTO(viewModel: RegisterViewModel): RegisterUserDTO {
        return RegisterUserDTO(
            name = viewModel.name,
            city = viewModel.city,
            address = viewModel.address,
            email = viewModel.email,
            password = viewModel.password,
            confirmPassword = viewModel.confirmPassword
        )
    }


    fun toRegisterAlertDTO(viewModel: RegisterAlertViewModel): RegisterAlertDTO {
        return RegisterAlertDTO(
            title = viewModel.title,
            category = viewModel.category,
            description = viewModel.description,
            address = viewModel.address,
            latitude = viewModel.latitude,
            longitude = viewModel.longitude,
            imageUri = viewModel.imageUri
        )
    }

    fun toEditRegisterUserDTO(viewModel: EditRegisterViewModel): EditRegisterUserDTO {
        return EditRegisterUserDTO(
            name = viewModel.name,
            city = viewModel.phoneNumber,
            address = viewModel.address,
            email = viewModel.email,
            password = viewModel.password,
            confirmPassword = viewModel.password,
            profileImageUri = viewModel.profileImageUri
        )
    }
}