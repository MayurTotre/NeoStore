package com.example.neostore.data.repository

import com.example.neostore.data.remote.UserApiService
import com.example.neostore.domain.model.RegistrationRequest
import com.example.neostore.domain.model.RegistrationResponse
import com.example.neostore.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: UserApiService
): UserRepository {
    override suspend fun registerUser(request: RegistrationRequest): RegistrationResponse {
        return apiService.regiterUser(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            password = request.password,
            confirmPassword = request.confirmPassword,
            gender = request.gender,
            phoneNo = request.phoneNo
        )
    }
}