package com.example.neostore.domain.model

data class ProductsResponse(
    val `data`: List<ProductsData>,
    val status: Int
)