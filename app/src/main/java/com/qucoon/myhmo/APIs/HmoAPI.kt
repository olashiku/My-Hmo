package com.qucoon.myhmo.APIs

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
import com.qucoon.myhmo.model.response.login.LoginResponse
import com.qucoon.myhmo.model.response.newpasswordReset.NewPasswordResetResponse
import com.qucoon.myhmo.model.response.resetpassword.ResetPasswordResponse
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

}