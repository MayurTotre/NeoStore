package com.example.neostore.data.remote

import com.example.neostore.domain.model.LoginResponse
import com.example.neostore.domain.model.RegistrationResponse
import com.example.neostore.domain.model.UserDataResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApiService {
    @FormUrlEncoded
    @POST("trainingapp/api/users/register")
    suspend fun registerUser(
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirm_password") confirmPassword: String,
        @Field("gender") gender: String,
        @Field("phone_no") phoneNo: String
    ): RegistrationResponse

    @FormUrlEncoded
    @POST("trainingapp/api/users/login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("trainingapp/api/users/getUserData")
    suspend fun getUserData(
        @Header("access_token") access_token: String
    ):UserDataResponse

}