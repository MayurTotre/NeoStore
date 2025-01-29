package com.example.neostore.domain.model

data class RegistrationRequest(
val firstName: String,
val lastName: String,
val email: String,
val password: String,
val confirmPassword: String,
val gender: String,
val phoneNo: String
)
