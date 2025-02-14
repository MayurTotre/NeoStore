package com.example.neostore.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.neostore.presentation.view.Address

@Dao
interface AddressDao {

    @Insert
    suspend fun insertAddress(address: Address)

    @Query("delete from address where id=:id")
    suspend fun deleteAddress(id : Int)

    @Query("select * from address")
    suspend fun getAddress(): List<Address>
}