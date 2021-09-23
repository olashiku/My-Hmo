package com.qucoon.myhmo.model.response.login

data class LoginResponse(
    val responsecode: String,
    val responsemessage: String,
    val user: User,
    val packageDetails: PackageDetails?
)