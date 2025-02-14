package com.example.neostore.data.repository

import com.example.neostore.data.remote.OrderDetailsApiService
import com.example.neostore.domain.model.OrderDetailsResponse
import com.example.neostore.domain.repository.OrderDetailsRepository
import retrofit2.http.Header
import javax.inject.Inject

class OrderDetailsRepositoryImpl @Inject constructor(
    private val orderDetailsApiService: OrderDetailsApiService
): OrderDetailsRepository{
    override suspend fun orderDetails(header: String, address: String ): OrderDetailsResponse {
        return orderDetailsApiService.orderAddress(
            accessToken = header,
            address = address
        )
    }

}