package com.example.neostore.domain.usecase

import com.example.neostore.domain.model.AddToCartRequest
import com.example.neostore.domain.model.AddToCartResponse
import com.example.neostore.domain.repository.AddToCartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.Header
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(private val addToCartRepository: AddToCartRepository) {
    suspend operator fun invoke(
        header: String,
        addToCartRequest: AddToCartRequest
    ): Flow<AddToCartResponse> = flow {
        emit(
            addToCartRepository.addToCart(
                header = header, addToCartRequest = addToCartRequest
            )
        )
    }
}