package com.example.neostore.data.remote

import com.example.neostore.domain.model.OrderDetailsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface OrderDetailsApiService {
    @FormUrlEncoded
    @POST("trainingapp/api/order")
    suspend fun orderAddress(
        @Header("access_token") accessToken: String,
        @Field("address") address: String
    ): OrderDetailsResponse

}