package com.example.neostore.data.repository

import com.example.neostore.data.remote.AddToCartApiService
import com.example.neostore.domain.model.AddToCartRequest
import com.example.neostore.domain.model.AddToCartResponse
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
}