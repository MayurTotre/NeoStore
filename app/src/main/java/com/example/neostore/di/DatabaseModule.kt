package com.example.neostore.di

import android.content.Context
import androidx.room.Room
import com.example.neostore.data.local.AddressDao
import com.example.neostore.data.local.AddressDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AddressDb{
        return Room.databaseBuilder(
            context.applicationContext,
            AddressDb::class.java,
            "addressDb"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDataDao(db: AddressDb): AddressDao{
        return db.getAddressDao()
    }
}