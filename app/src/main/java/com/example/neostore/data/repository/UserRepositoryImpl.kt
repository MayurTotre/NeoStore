package com.example.neostore.data.repository

import com.example.neostore.data.remote.UserApiService
import com.example.neostore.domain.model.LoginRequest
import com.example.neostore.domain.model.LoginResponse
import com.example.neostore.domain.model.RegistrationRequest
import com.example.neostore.domain.model.RegistrationResponse
import com.example.neostore.domain.model.UserDataResponse
import com.example.neostore.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: UserApiService
): UserRepository {
    override suspend fun registerUser(request: RegistrationRequest): RegistrationResponse {
        return apiService.registerUser(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            password = request.password,
            confirmPassword = request.confirmPassword,
            gender = request.gender,
            phoneNo = request.phoneNo
        )
    }

    override suspend fun loginUser(request: LoginRequest): LoginResponse {
        return apiService.loginUser(
            email = request.email,
            password = request.password
        )
    }

    override suspend fun getUserData(accessToken: String): UserDataResponse {
        return apiService.getUserData(access_token = accessToken)
    }

}