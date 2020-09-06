package com.qucoon.myhmo.model.request.verifyemail

data class verifyEmailRequest(
    val email: String,
    val token: String,
    val uniqueref: String
)