package com.qucoon.myhmo.repository

import com.example.neptune.utils.Constants
import com.nubis.watchguard.utils.UseCaseResult
import com.qucoon.myhmo.APIs.HmoAPI
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.savePref
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

interface OutsideRepository {
    suspend  fun loginuser(loginRequest: LoginRequest): UseCaseResult<LoginResponse>
    suspend fun saveLoginResponse(request: LoginRequest,response: LoginResponse)
    suspend fun registeruser(registerRequest: RegisterRequest):UseCaseResult<RegisterResponse>
    suspend fun authEmail(authemailRequest: AuthemailRequest):UseCaseResult<AuthemailResponse>
    suspend fun verifyEmail(verifyEmailRequest: verifyEmailRequest):UseCaseResult<DefaultResponse>
    suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): UseCaseResult<ResetPasswordResponse>
    suspend fun authenticatePassword(authenticatePasswordRequest: AuthenticatePasswordRequest):UseCaseResult<AuthenticatePasswordResponse>
    suspend fun newPasswordReset(newpasswordResetRequest: NewpasswordResetRequest):UseCaseResult<NewPasswordResetResponse>
}

  class OutsideRepoimpl(private val hmoAPI: HmoAPI,private val paperPrefs: PaperPrefs):BaseRepository(),OutsideRepository{

      override  suspend  fun loginuser(loginRequest: LoginRequest): UseCaseResult<LoginResponse> {
          return safeApiCall(loginRequest,hmoAPI::login, { response ->response.responsecode == Constants.SUCCESS},this::saveLoginResponse)
      }

      override suspend fun saveLoginResponse(request: LoginRequest, response: LoginResponse) {
          saveLoginResponsee(response)
      }

      override suspend fun registeruser(registerRequest: RegisterRequest): UseCaseResult<RegisterResponse> {
          return safeApiCall(registerRequest,hmoAPI::register, { response ->response.responsecode == "00"})
      }

      override suspend fun authEmail(authemailRequest: AuthemailRequest): UseCaseResult<AuthemailResponse> {
          return safeApiCall(authemailRequest,hmoAPI::authemail, { response ->response.responsecode == Constants.SUCCESS})
      }

      override suspend fun verifyEmail(verifyEmailRequest: verifyEmailRequest): UseCaseResult<DefaultResponse> {
          return safeApiCall(verifyEmailRequest,hmoAPI::verifyemail, { response ->response.responsecode == Constants.SUCCESS})

      }

      override suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): UseCaseResult<ResetPasswordResponse> {
          return safeApiCall(resetPasswordRequest,hmoAPI::resetpassword, { response ->response.responsecode == Constants.SUCCESS})
      }

      override suspend fun authenticatePassword(authenticatePasswordRequest: AuthenticatePasswordRequest): UseCaseResult<AuthenticatePasswordResponse> {
          return safeApiCall(authenticatePasswordRequest,hmoAPI::authenticatePassword, { response ->response.responsecode == Constants.SUCCESS})
      }

      override suspend fun newPasswordReset(newpasswordResetRequest: NewpasswordResetRequest): UseCaseResult<NewPasswordResetResponse> {
          return safeApiCall(newpasswordResetRequest,hmoAPI::newpasswordReset, { response ->response.responsecode == Constants.SUCCESS})
      }

      private fun saveLoginResponsee(response: LoginResponse) {
          paperPrefs.savePref(PaperPrefs.FIRSTNAME,response.user.firstName)
          paperPrefs.savePref(PaperPrefs.LASTNAME,response.user.lastName)
          paperPrefs.savePref(PaperPrefs.EMAIL,response.user.email)
          paperPrefs.savePref(PaperPrefs.PHONE,response.user.phone)
          paperPrefs.savePref(PaperPrefs.IMAGE,response.user.image)
          paperPrefs.savePref(PaperPrefs.ENROLSTATUS,response.user.status)
          paperPrefs.savePref(PaperPrefs.USERID,response.user.userid.toString())



      }


  }