package com.example.neostore.domain.model

data class RegistrationResponse(
    val `data`: Data,
    val message: String,
    val status: Int,
    val user_msg: String
)