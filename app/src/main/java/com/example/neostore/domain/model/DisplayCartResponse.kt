package com.example.neostore.domain.model

data class DisplayCartResponse(
    val count: Int,
    val `data`: List<CartData>,
    val status: Int,
    val total: Int
)