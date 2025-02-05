package com.example.neostore.domain.repository

import com.example.neostore.domain.model.LoginRequest
import com.example.neostore.domain.model.LoginResponse
import com.example.neostore.domain.model.RegistrationRequest
import com.example.neostore.domain.model.RegistrationResponse
import com.example.neostore.domain.model.UserDataResponse

interface UserRepository {
    suspend fun registerUser(request: RegistrationRequest): RegistrationResponse

    suspend fun loginUser(request: LoginRequest): LoginResponse

    suspend fun getUserData(accessToken: String): UserDataResponse
}