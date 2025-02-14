package com.example.neostore.di

import com.example.neostore.data.repository.OrderDetailsRepositoryImpl
import com.example.neostore.data.repository.ProductsRepositoryImpl
import com.example.neostore.domain.repository.OrderDetailsRepository
import com.example.neostore.domain.repository.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class OrderDetailsModule {
    @Provides
    @Singleton
    fun provideOrderDetailsRepository(orderDetailsRepositoryImpl: OrderDetailsRepositoryImpl): OrderDetailsRepository {
        return orderDetailsRepositoryImpl
    }
}