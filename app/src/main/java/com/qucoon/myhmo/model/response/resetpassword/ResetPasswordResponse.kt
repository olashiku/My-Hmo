package com.qucoon.myhmo.model.response.resetpassword

data class ResetPasswordResponse(
    val responsecode: String,
    val responsemessage: String,
    val uniqueref: String
)