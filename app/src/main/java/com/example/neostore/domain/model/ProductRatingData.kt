package com.example.neostore.domain.model

data class ProductRatingData(
    val cost: Int,
    val created: String,
    val description: String,
    val id: Int,
    val modified: String,
    val name: String,
    val producer: String,
    val product_category_id: Int,
    val rating: Double,
    val view_count: Int
)