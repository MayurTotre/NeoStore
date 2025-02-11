package com.example.neostore.domain.repository

import com.example.neostore.domain.model.ProductDetailsRequest
import com.example.neostore.domain.model.ProductDetailsResponse
import com.example.neostore.domain.model.ProductRatingRequest
import com.example.neostore.domain.model.ProductRatingResponse
import com.example.neostore.domain.model.ProductRequest
import com.example.neostore.domain.model.ProductsResponse

interface ProductsRepository {
    suspend fun getProducts(productRequest: ProductRequest): ProductsResponse
    suspend fun getProductDetails(productDetailsRequest: ProductDetailsRequest): ProductDetailsResponse
    suspend fun setProductRating(productRatingRequest: ProductRatingRequest): ProductRatingResponse
}