package com.qucoon.myhmo.model.request.enroluser

data class EnroluserRequest(
    val amount: String,
    val category_type: String,
    val customer_email: String,
    val customerid: String,
    val duration: String,
    val firstName: String,
    val lastName: String,
    val schedule_payment: String,
    val subscriber: String,
    val subtype: String,
    val user_package: String
)