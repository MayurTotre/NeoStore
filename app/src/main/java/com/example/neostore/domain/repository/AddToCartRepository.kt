package com.example.neostore.domain.repository

import com.example.neostore.domain.model.AddToCartRequest
import com.example.neostore.domain.model.AddToCartResponse
import okhttp3.internal.http2.Header

interface AddToCartRepository {
    suspend fun addToCart(header: String, addToCartRequest: AddToCartRequest): AddToCartResponse
}