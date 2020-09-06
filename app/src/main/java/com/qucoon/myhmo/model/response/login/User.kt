package com.qucoon.myhmo.model.response.login

data class User(
    val email: String,
    val email_status: String,
    val firstName: String,
    val image: String,
    val lastName: String,
    val latitude: String,
    val longitude: String,
    val phone: String,
    val userid: Int
)