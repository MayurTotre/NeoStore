package com.example.neostore.domain.model

data class OrderListResponse(
    val `data`: List<OrderListData>,
    val message: String,
    val status: Int,
    val user_msg: String
)