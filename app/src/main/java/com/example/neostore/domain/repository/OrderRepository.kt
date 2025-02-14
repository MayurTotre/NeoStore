package com.example.neostore.domain.repository

import com.example.neostore.presentation.view.Address

interface OrderRepository {

    suspend fun getAllAddress(): List<Address>

    suspend fun addAddress(address: Address)

    suspend fun deleteAddress(id:Int)
}