package com.example.neostore.domain.model

data class DeleteProductResponse(
    val `data`: Boolean,
    val message: String,
    val status: Int,
    val total_carts: Int,
    val user_msg: String
)