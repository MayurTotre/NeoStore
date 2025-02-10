package com.example.neostore.data.repository

import com.example.neostore.data.remote.ProductsApiService
import com.example.neostore.domain.model.ProductRequest
import com.example.neostore.domain.model.ProductsResponse
import com.example.neostore.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsApiService: ProductsApiService
): ProductsRepository{
    override suspend fun getProducts(productRequest: ProductRequest): ProductsResponse {
        return productsApiService.getProducts(productRequest = productRequest.product_category_id)
    }
}