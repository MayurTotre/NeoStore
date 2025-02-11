package com.example.neostore.domain.model

data class ProductDetailsData(
    val cost: Int,
    val created: String,
    val description: String,
    val id: Int,
    val modified: String,
    val name: String,
    val producer: String,
    val product_category_id: Int,
    val product_images: List<ProductImage>,
    val rating: Int,
    val view_count: Int
)