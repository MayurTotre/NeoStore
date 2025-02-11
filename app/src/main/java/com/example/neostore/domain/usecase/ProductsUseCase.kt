package com.example.neostore.domain.usecase

import com.example.neostore.domain.model.ProductDetailsRequest
import com.example.neostore.domain.model.ProductDetailsResponse
import com.example.neostore.domain.model.ProductRatingRequest
import com.example.neostore.domain.model.ProductRatingResponse
import com.example.neostore.domain.model.ProductRequest
import com.example.neostore.domain.model.ProductsResponse
import com.example.neostore.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductsUseCase @Inject constructor(private val productsRepository: ProductsRepository){
    suspend operator fun invoke(productRequest: ProductRequest): Flow<ProductsResponse> = flow{
        emit(productsRepository.getProducts(productRequest))
    }

    suspend fun invokeProductDetails(productDetailsRequest: ProductDetailsRequest): Flow<ProductDetailsResponse> = flow{
        emit(productsRepository.getProductDetails(productDetailsRequest))
    }

    suspend fun invokeProductRating(productRatingRequest: ProductRatingRequest): Flow<ProductRatingResponse> = flow{
        emit(productsRepository.setProductRating(productRatingRequest))
    }
}