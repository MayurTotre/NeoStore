package com.example.neostore.domain.model

data class ProductRatingRequest(
   val product_id: String,
   val rating: Int = 3
)