package com.example.neostore.domain.repository

import android.health.connect.HealthConnectException
import com.example.neostore.domain.model.AddToCartRequest
import com.example.neostore.domain.model.AddToCartResponse
import com.example.neostore.domain.model.DeleteProductRequest
import com.example.neostore.domain.model.DeleteProductResponse
import com.example.neostore.domain.model.DisplayCartResponse
import com.example.neostore.domain.model.EditCartRequest
import com.example.neostore.domain.model.EditCartResponse

interface AddToCartRepository {
    suspend fun addToCart(header: String, addToCartRequest: AddToCartRequest): AddToCartResponse

    suspend fun displayCart(header: String): DisplayCartResponse

    suspend fun deleteProduct(header: String, deleteProductRequest: DeleteProductRequest): DeleteProductResponse

    suspend fun editCart(header: String, editCartRequest: EditCartRequest): EditCartResponse

}