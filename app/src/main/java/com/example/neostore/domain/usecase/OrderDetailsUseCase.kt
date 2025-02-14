package com.example.neostore.domain.usecase

import com.example.neostore.data.repository.OrderDetailsRepositoryImpl
import com.example.neostore.domain.model.OrderDetailsResponse
import com.example.neostore.domain.repository.OrderDetailsRepository
import kotlinx.coroutines.flow.flow
import retrofit2.http.Header
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderDetailsUseCase @Inject constructor (
    private val orderDetailsRepository: OrderDetailsRepository
) {
    suspend fun invokeOrderDetails(
        header: String,
        address: String
    ): Flow<OrderDetailsResponse> = flow {
        emit(orderDetailsRepository.orderDetails(header, address))
    }
}