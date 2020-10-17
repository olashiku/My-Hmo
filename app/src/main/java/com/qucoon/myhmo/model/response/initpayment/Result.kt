package com.qucoon.myhmo.model.response.initpayment

data class Result(
    val access_code: String,
    val authorization_url: String,
    val reference: String
)