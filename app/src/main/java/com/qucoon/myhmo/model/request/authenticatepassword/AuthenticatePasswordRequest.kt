package com.qucoon.myhmo.model.request.authenticatepassword

data class AuthenticatePasswordRequest(
    val email: String,
    val token: String,
    val uniqueref: String
)