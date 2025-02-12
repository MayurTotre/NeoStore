package com.example.neostore.domain.usecase

import com.example.neostore.domain.model.AddToCartRequest
import com.example.neostore.domain.model.AddToCartResponse
import com.example.neostore.domain.model.DeleteProductRequest
import com.example.neostore.domain.model.DeleteProductResponse
import com.example.neostore.domain.model.DisplayCartResponse
import com.example.neostore.domain.model.EditCartRequest
import com.example.neostore.domain.model.EditCartResponse
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

    suspend fun invokeCartItems(
        header: String
    ): Flow<DisplayCartResponse> = flow {
        emit(
            addToCartRepository.displayCart(
                header = header
            )
        )
    }

    suspend fun invokeDeleteProduct(
        header: String,
        deleteProductRequest: DeleteProductRequest
    ):Flow<DeleteProductResponse> = flow {
        emit(
            addToCartRepository.deleteProduct(
                header = header,
                deleteProductRequest = deleteProductRequest
            )
        )
    }

    suspend fun invokeEditCart(
        header: String,
        editCartRequest: EditCartRequest
    ):Flow<EditCartResponse> = flow {
        emit(
            addToCartRepository.editCart(
                header = header,
                editCartRequest = editCartRequest
            )
        )
    }
}