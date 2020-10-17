package com.qucoon.myhmo.model.request.confirmpaystack

data class PaystackConfirmationRequest(
    val email: String,
    val reference: String
)