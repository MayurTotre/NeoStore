package com.example.neostore.domain.repository

import com.example.neostore.domain.model.RegistrationRequest
import com.example.neostore.domain.model.RegistrationResponse

interface UserRepository {
    suspend fun registerUser(request: RegistrationRequest): RegistrationResponse
}