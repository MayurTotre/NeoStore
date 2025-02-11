package com.example.neostore.di

import com.example.neostore.data.repository.AddToCartRepositoryImpl
import com.example.neostore.data.repository.ProductsRepositoryImpl
import com.example.neostore.domain.repository.AddToCartRepository
import com.example.neostore.domain.repository.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AddToCartModule {
    @Provides
    @Singleton
    fun provideAddToCartRepository(addToCartRepositoryImpl: AddToCartRepositoryImpl): AddToCartRepository {
        return addToCartRepositoryImpl
    }
}
