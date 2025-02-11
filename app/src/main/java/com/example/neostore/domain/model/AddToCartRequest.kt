package com.example.neostore.domain.model

data class AddToCartRequest(
    val product_id: Int,
    val quantity: Int
)