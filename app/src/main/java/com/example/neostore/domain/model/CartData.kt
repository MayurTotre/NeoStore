package com.example.neostore.domain.model

data class CartData(
    val id: Int,
    val product: CartProducts,
    val product_id: Int,
    val quantity: Int
)