package com.example.neostore.data.remote

import com.example.neostore.domain.model.AddToCartResponse
import com.example.neostore.domain.model.DeleteProductResponse
import com.example.neostore.domain.model.DisplayCartResponse
import com.example.neostore.domain.model.EditCartResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AddToCartApiService {
    @FormUrlEncoded
    @POST("trainingapp/api/addToCart")
    suspend fun addToCart(
        @Header("access_token") accessToken: String,
        @Field("product_id") productId: Int,
        @Field("quantity") quantity: Int
    ): AddToCartResponse

    @GET("trainingapp/api/cart")
    suspend fun displayCart(
        @Header("access_token") accessToken: String
    ): DisplayCartResponse

    @FormUrlEncoded
    @POST("trainingapp/api/deleteCart")
    suspend fun deleteProduct(
        @Header("access_token") accessToken: String,
        @Field("product_id") productId: Int,
    ): DeleteProductResponse

    @FormUrlEncoded
    @POST("trainingapp/api/editCart")
    suspend fun editCart(
        @Header("access_token") accessToken: String,
        @Field("product_id") productId: Int,
        @Field("quantity") quantity: Int
    ): EditCartResponse
}