package com.example.neostore.domain.model

data class CartProducts(
    val cost: Int,
    val id: Int,
    val name: String,
    val product_category: String,
    val product_images: String,
    val sub_total: Int
)