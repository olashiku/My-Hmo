package com.qucoon.myhmo.model.request.login

data class LoginRequest(
    val latitude: String,
    val longitude: String,
    val password: String,
    val username: String
)