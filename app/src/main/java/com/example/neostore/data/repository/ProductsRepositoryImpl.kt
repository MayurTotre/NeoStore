package com.example.neostore.data.repository

import com.example.neostore.data.remote.ProductsApiService
import com.example.neostore.domain.model.ProductDetailsRequest
import com.example.neostore.domain.model.ProductDetailsResponse
import com.example.neostore.domain.model.ProductRatingRequest
import com.example.neostore.domain.model.ProductRatingResponse
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

    override suspend fun getProductDetails(productDetailsRequest: ProductDetailsRequest): ProductDetailsResponse {
        return productsApiService.getProductDetails(productDetailsRequest = productDetailsRequest.product_id)
    }

    override suspend fun setProductRating(productRatingRequest: ProductRatingRequest): ProductRatingResponse {
        return productsApiService.setProductRating(productRatingRequest = productRatingRequest.product_id)
    }

}