package com.example.neostore.domain.model

data class ProductRatingResponse(
    val `data`: ProductRatingData,
    val message: String,
    val status: Int,
    val user_msg: String
)