package com.qucoon.myhmo.model.request.newpasswordReset

data class NewpasswordResetRequest(
    val email: String,
    val newpassword: String
)