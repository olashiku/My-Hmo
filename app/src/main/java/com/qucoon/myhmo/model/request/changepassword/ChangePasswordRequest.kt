package com.qucoon.myhmo.model.request.changepassword

data class ChangePasswordRequest(
    val email: String,
    val newpassword: String,
    val oldpassword: String
)