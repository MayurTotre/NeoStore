package com.example.neostore.domain.model

data class EditCartRequest(
    val product_id: Int,
    val quantity: Int
)