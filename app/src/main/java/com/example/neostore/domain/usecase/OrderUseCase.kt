package com.example.neostore.domain.usecase

import com.example.neostore.domain.repository.OrderRepository
import com.example.neostore.presentation.view.Address
import javax.inject.Inject

class OrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {

    suspend fun invokeaddAddress(address: Address){
        orderRepository.addAddress(address)
    }
    suspend fun invokegetAddress(): List<Address>{
       return orderRepository.getAllAddress()
    }

    suspend fun invokeDeleteAddress(id: Int){
        return orderRepository.deleteAddress(id)
    }


}