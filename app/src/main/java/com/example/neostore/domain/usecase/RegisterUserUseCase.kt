package com.example.neostore.domain.usecase

import com.example.neostore.domain.repository.UserRepository
import com.example.neostore.domain.model.RegistrationRequest
import com.example.neostore.domain.model.RegistrationResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(request: RegistrationRequest): Flow<RegistrationResponse> = flow{
        emit(repository.registerUser(request))
    }
}