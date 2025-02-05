package com.example.neostore.domain.usecase

import com.example.neostore.domain.model.LoginRequest
import com.example.neostore.domain.model.LoginResponse
import com.example.neostore.domain.repository.UserRepository
import com.example.neostore.domain.model.RegistrationRequest
import com.example.neostore.domain.model.RegistrationResponse
import com.example.neostore.domain.model.UserDataResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend fun registerinvoke(request: RegistrationRequest): Flow<RegistrationResponse> = flow{
        emit(repository.registerUser(request))
    }

    suspend fun logininvoke(request: LoginRequest): Flow<LoginResponse> = flow {
        emit(repository.loginUser(request))
    }

    suspend fun getUserData(access_token: String): Flow<UserDataResponse> = flow{
        emit(repository.getUserData(access_token))
    }
}