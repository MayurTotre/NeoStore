package com.example.neostore.data.remote

import com.example.neostore.domain.model.RegistrationResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApiService {
    @FormUrlEncoded
    @POST("trainingapp/api/users/register")
    suspend fun regiterUser(
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirm_password") confirmPassword: String,
        @Field("gender") gender: String,
        @Field("phone_no") phoneNo: String
    ): RegistrationResponse
}