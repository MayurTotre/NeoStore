package com.example.neostore.domain.model

data class ProductRequest(
    val product_category_id: String,
    val limit: Int = 10,
    val page: Int = 1
)