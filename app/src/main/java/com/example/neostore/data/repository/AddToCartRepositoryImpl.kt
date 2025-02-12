package com.example.neostore.data.repository

import com.example.neostore.data.remote.AddToCartApiService
import com.example.neostore.domain.model.AddToCartRequest
import com.example.neostore.domain.model.AddToCartResponse
import com.example.neostore.domain.model.DeleteProductRequest
import com.example.neostore.domain.model.DeleteProductResponse
import com.example.neostore.domain.model.DisplayCartResponse
import com.example.neostore.domain.model.EditCartRequest
import com.example.neostore.domain.model.EditCartResponse
import com.example.neostore.domain.repository.AddToCartRepository
import retrofit2.http.Header
import javax.inject.Inject

class AddToCartRepositoryImpl @Inject constructor(
    private val addToCartApiService: AddToCartApiService
): AddToCartRepository {
    override suspend fun addToCart(header: String, addToCartRequest: AddToCartRequest): AddToCartResponse {
        return addToCartApiService.addToCart(
            accessToken = header,
            productId = addToCartRequest.product_id,
            quantity = addToCartRequest.quantity
        )
    }

    override suspend fun displayCart(header: String): DisplayCartResponse {
        return addToCartApiService.displayCart(
            accessToken = header
        )
    }

    override suspend fun deleteProduct(
        header: String,
        deleteProductRequest: DeleteProductRequest
    ): DeleteProductResponse {
        return addToCartApiService.deleteProduct(
            accessToken = header,
            productId = deleteProductRequest.product_id
        )
    }

    override suspend fun editCart(
        header: String,
        editCartRequest: EditCartRequest
    ): EditCartResponse {
        return addToCartApiService.editCart(
            accessToken = header,
            productId = editCartRequest.product_id,
            quantity = editCartRequest.quantity
        )
    }
}