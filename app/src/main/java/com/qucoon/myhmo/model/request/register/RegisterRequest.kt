package com.qucoon.myhmo.model.request.register

data class RegisterRequest(
    val category: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val latitude: String,
    val longitude: String,
    val password: String,
    val phone: String,
    val pushid: String
)