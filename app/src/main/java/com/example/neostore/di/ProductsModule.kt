package com.example.neostore.di

import com.example.neostore.data.repository.ProductsRepositoryImpl
import com.example.neostore.domain.repository.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProductsModule {
    @Provides
    @Singleton
    fun provideProductsRepository(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository{
        return productsRepositoryImpl
    }
}