package com.example.neostore.data.remote

import com.example.neostore.domain.model.ProductsResponse
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApiService {
    @GET("trainingapp/api/products/getList")
    suspend fun getProducts(
        @Query("product_category_id") productRequest: String
    ): ProductsResponse

}