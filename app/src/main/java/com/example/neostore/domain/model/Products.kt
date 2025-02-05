package com.example.neostore.domain.model

data class Products(
    val product_categories: List<ProductCategory>,
    val total_carts: Int,
    val total_orders: Int,
    val user_data: Data
)