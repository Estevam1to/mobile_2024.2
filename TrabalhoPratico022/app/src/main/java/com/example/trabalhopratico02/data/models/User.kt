package com.example.trabalhopratico02.data.models

data class User(
    val id: Int,
    val name: String,
    val email: String,

)

data class CreateUserRequest(
    val name: String,
    val email: String,
)