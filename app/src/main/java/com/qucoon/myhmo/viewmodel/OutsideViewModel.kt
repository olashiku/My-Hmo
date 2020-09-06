package com.qucoon.myhmo.viewmodel

import com.example.neptune.viewmodel.BaseViewModel
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.myhmo.model.request.authemail.AuthemailRequest
import com.qucoon.myhmo.model.request.authenticatepassword.AuthenticatePasswordRequest
import com.qucoon.myhmo.model.request.login.LoginRequest
import com.qucoon.myhmo.model.request.newpasswordReset.NewpasswordResetRequest
import com.qucoon.myhmo.model.request.register.RegisterRequest
import com.qucoon.myhmo.model.request.resetpassword.ResetPasswordRequest
import com.qucoon.myhmo.model.request.verifyemail.verifyEmailRequest
import com.qucoon.myhmo.model.response.DefaultResponse
import com.qucoon.myhmo.model.response.Register.RegisterResponse
import com.qucoon.myhmo.model.response.authemail.AuthemailResponse
import com.qucoon.myhmo.model.response.authenticatepassword.AuthenticatePasswordResponse
import com.qucoon.myhmo.model.response.login.LoginResponse
import com.qucoon.myhmo.model.response.newpasswordReset.NewPasswordResetResponse
import com.qucoon.myhmo.model.response.resetpassword.ResetPasswordResponse
import com.qucoon.myhmo.repository.OutsideRepository
import com.qucoon.nibbs.utils.SingleLiveEvent

class OutsideViewModel (private val outsideRepository: OutsideRepository, private val paperPrefs: PaperPrefs):BaseViewModel(){

    val loginResponse = SingleLiveEvent<LoginResponse>()
    val defaultResponse= SingleLiveEvent<DefaultResponse>()
    val authemailResponse= SingleLiveEvent<AuthemailResponse>()
    val registerResponse = SingleLiveEvent<RegisterResponse>()
    var resetPasswordResponse= SingleLiveEvent<ResetPasswordResponse>()
    var authenticatePasswordResponse= SingleLiveEvent<AuthenticatePasswordResponse>()
    var newPasswordResetResponse = SingleLiveEvent<NewPasswordResetResponse>()


    fun newPasswordReset(email: String,newpassword:String){
        var request = NewpasswordResetRequest(email= email, newpassword = newpassword)
        apiRequest(request,outsideRepository::newPasswordReset,newPasswordResetResponse,{it.responsemessage})
    }

    fun authenticatePassword(email: String,uniqueref: String,token: String){
        var request = AuthenticatePasswordRequest(email= email, token = token,uniqueref = uniqueref)
        apiRequest(request,outsideRepository::authenticatePassword,authenticatePasswordResponse,{it.responsemessage})
    }


     fun resetPassword(email:String){
         var request= ResetPasswordRequest(email= email)
         apiRequest(request,outsideRepository::resetPassword,resetPasswordResponse,{it.responsemessage})
     }

     fun verifyEmail(email:String,token:String,uniqueref:String){
         var request= verifyEmailRequest(email= email,token = token,uniqueref = uniqueref)
         apiRequest(request,outsideRepository::verifyEmail,defaultResponse,{it.responsemessage})
     }

    fun authEmail(email:String){
        var request= AuthemailRequest(email= email)
        apiRequest(request,outsideRepository::authEmail,authemailResponse,{it.responsemessage})
    }

     fun loginUser(password:String,username:String){
     val request = LoginRequest(paperPrefs.getStringPref(PaperPrefs.LATITUDE), paperPrefs.getStringPref(PaperPrefs.LONGITUDE),password,username)
        apiRequest(request,outsideRepository::loginuser,loginResponse,{it.responsemessage})
    }

     fun registerUser(category:String,email:String,firstname:String,lastname:String,password:String,phone:String){
     val request= RegisterRequest(category = category,email=email,firstName = firstname,lastName = lastname, latitude = paperPrefs.getStringPref(PaperPrefs.LATITUDE), longitude = paperPrefs.getStringPref(PaperPrefs.LONGITUDE),password = password, phone = phone,pushid = paperPrefs.getStringPref(PaperPrefs.PUSHID))
     apiRequest(request,outsideRepository::registeruser,registerResponse,{it.responsemessage})
     }
}