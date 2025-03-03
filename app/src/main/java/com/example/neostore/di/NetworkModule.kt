package com.example.neostore.di

import com.example.neostore.data.remote.AddToCartApiService
import com.example.neostore.data.remote.OrderDetailsApiService
import com.example.neostore.data.remote.ProductsApiService
import com.example.neostore.data.remote.UserApiService
import com.example.neostore.domain.model.AddToCartResponse
import com.example.neostore.domain.repository.OrderDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        val level = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(level)
            .build()


        return Retrofit.Builder()
            .baseUrl("http://staging.php-dev.in:8844/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApiService{
        return retrofit.create(UserApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApiService(retrofit: Retrofit): ProductsApiService{
        return retrofit.create(ProductsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAddToCartRepository(retrofit: Retrofit): AddToCartApiService{
        return retrofit.create(AddToCartApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideOrderDetailRepository(retrofit: Retrofit): OrderDetailsApiService{
        return retrofit.create(OrderDetailsApiService::class.java)
    }
}
