package com.example.neostore.domain.model

data class LoginRequest(
    val email: String,
    val password: String,
)