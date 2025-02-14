package com.example.neostore.domain.repository

import android.location.Address
import com.example.neostore.domain.model.OrderDetailsResponse

interface OrderDetailsRepository {

    suspend fun orderDetails(header: String, address: String): OrderDetailsResponse
}