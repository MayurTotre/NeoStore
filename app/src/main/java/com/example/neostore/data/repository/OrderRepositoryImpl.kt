package com.example.neostore.data.repository

import com.example.neostore.data.local.AddressDao
import com.example.neostore.domain.repository.OrderRepository
import com.example.neostore.presentation.view.Address
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val addressDao: AddressDao
): OrderRepository {
    override suspend fun getAllAddress(): List<Address> {
        return addressDao.getAddress()
    }

    override suspend fun addAddress(address: Address) {
        return addressDao.insertAddress(address)
    }

    override suspend fun deleteAddress(id: Int) {
        return addressDao.deleteAddress(id)
    }

}