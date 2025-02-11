package com.example.neostore.data.remote

import com.example.neostore.domain.model.AddToCartResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface AddToCartApiService {
    @FormUrlEncoded
    @POST("trainingapp/api/addToCart")
    suspend fun addToCart(
        @Header("access_token") accessToken: String,
        @Field("product_id") productId: Int,
        @Field("quantity") quantity: Int
    ):AddToCartResponse
}