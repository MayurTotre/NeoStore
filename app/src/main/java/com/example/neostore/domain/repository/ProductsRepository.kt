package com.example.neostore.domain.repository

import com.example.neostore.domain.model.ProductRequest
import com.example.neostore.domain.model.ProductsResponse

interface ProductsRepository {
    suspend fun getProducts(productRequest: ProductRequest): ProductsResponse
}