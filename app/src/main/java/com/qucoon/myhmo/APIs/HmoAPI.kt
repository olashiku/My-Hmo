package com.qucoon.myhmo.APIs

import com.nubis.watchguard.utils.UseCaseResult
import com.qucoon.myhmo.model.request.newpasswordReset.NewpasswordResetRequest
import com.qucoon.myhmo.model.request.authemail.AuthemailRequest
import com.qucoon.myhmo.model.request.authenticatepassword.AuthenticatePasswordRequest
import com.qucoon.myhmo.model.request.login.LoginRequest
import com.qucoon.myhmo.model.request.register.RegisterRequest
import com.qucoon.myhmo.model.request.resetpassword.ResetPasswordRequest
import com.qucoon.myhmo.model.request.verifyemail.verifyEmailRequest
import com.qucoon.myhmo.model.response.DefaultResponse
import com.qucoon.myhmo.model.response.Register.RegisterResponse
import com.qucoon.myhmo.model.response.authemail.AuthemailResponse
import com.qucoon.myhmo.model.response.authenticatepassword.AuthenticatePasswordResponse
import com.qucoon.myhmo.model.response.changepassword.ChangePasswordResponse
import com.qucoon.myhmo.model.response.login.LoginResponse
import com.qucoon.myhmo.model.response.newpasswordReset.NewPasswordResetResponse
import com.qucoon.myhmo.model.response.resetpassword.ResetPasswordResponse
import com.qucoon.myhmo.model.request.changepassword.ChangePasswordRequest
import com.qucoon.myhmo.model.request.confirmpaystack.PaystackConfirmationRequest
import com.qucoon.myhmo.model.request.enrolmentcomplete.enrolmentcompleteRequest
import com.qucoon.myhmo.model.request.enroluser.EnroluserRequest
import com.qucoon.myhmo.model.request.initpaystack.InitpaymentRequest
import com.qucoon.myhmo.model.response.confirmpaystack.PaystackConfirmationResponse
import com.qucoon.myhmo.model.response.enrolmentcompleteResponse
import com.qucoon.myhmo.model.response.enroluser.EnroluserResponse
import com.qucoon.myhmo.model.response.initpayment.InitpaymentResponse

import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface HmoAPI {

    @POST("login")
    fun login(@Body params: LoginRequest): Deferred<LoginResponse>

    @POST("register")
    fun register(@Body params:RegisterRequest):Deferred<RegisterResponse>

    @POST("authemail")
    fun authemail(@Body params: AuthemailRequest): Deferred<AuthemailResponse>

    @POST("verifyemail")
    fun verifyemail(@Body params: verifyEmailRequest): Deferred<DefaultResponse>

    @POST("resetpassword")
    fun resetpassword(@Body params: ResetPasswordRequest):Deferred<ResetPasswordResponse>

    @POST("authenticatePassword")
    fun authenticatePassword(@Body params: AuthenticatePasswordRequest):Deferred<AuthenticatePasswordResponse>

    @POST("newpasswordReset")
    fun newpasswordReset(@Body params: NewpasswordResetRequest):Deferred<NewPasswordResetResponse>

   @POST("changepassword")
    fun changePassword(@Body params:ChangePasswordRequest): Deferred<ChangePasswordResponse>

     @POST("initpayment")
     fun initPaystack(@Body params: InitpaymentRequest): Deferred<InitpaymentResponse>

    @POST("confirmpayment")
    fun confirmpayment(@Body params: PaystackConfirmationRequest): Deferred<PaystackConfirmationResponse>

    @POST("enroluser")
    fun enrolusers(@Body params: EnroluserRequest): Deferred<EnroluserResponse>

    @POST("enrolmentcomplete")
    fun enrolmentcomplete(@Body params: enrolmentcompleteRequest):Deferred<enrolmentcompleteResponse>

}