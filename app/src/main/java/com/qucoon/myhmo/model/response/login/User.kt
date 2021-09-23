package com.qucoon.myhmo.model.response.login

data class User(
    val email: String,
    val email_status: String,
    val firstName: String,
    val image: String,
    val lastName: String,
    val latitude: String,
    val longitude: String,
    val phone: String,
    val userid: String,
    val status:String,
     val enrolment_status:String,
    val activation_status:String,
    val address:String,
    val gender:String,
    val dob:String
)