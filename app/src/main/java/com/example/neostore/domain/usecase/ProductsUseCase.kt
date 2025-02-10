package com.example.neostore.domain.usecase

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
}