package com.example.neostore.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.neostore.presentation.view.Address

@Database(entities = [Address::class], version=1)
abstract class AddressDb: RoomDatabase() {
    abstract fun getAddressDao(): AddressDao
}