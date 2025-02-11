package com.example.neostore.data.remote

import com.example.neostore.domain.model.ProductDetailsResponse
import com.example.neostore.domain.model.ProductRatingResponse
import com.example.neostore.domain.model.ProductsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductsApiService {
    @GET("trainingapp/api/products/getList")
    suspend fun getProducts(
        @Query("product_category_id") productRequest: String
    ): ProductsResponse

    @GET("trainingapp/api/products/getDetail")
    suspend fun getProductDetails(
        @Query("product_id") productDetailsRequest: String
    ): ProductDetailsResponse

    @FormUrlEncoded
    @POST("trainingapp/api/products/setRating")
    suspend fun setProductRating(
        @Field("product_id") productRatingRequest: String
    ): ProductRatingResponse
}