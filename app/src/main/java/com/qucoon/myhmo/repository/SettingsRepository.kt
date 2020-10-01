package com.qucoon.myhmo.repository

import com.nubis.watchguard.utils.UseCaseResult
import com.qucoon.myhmo.APIs.HmoAPI
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.model.request.changepassword.ChangePasswordRequest
import com.qucoon.myhmo.model.response.changepassword.ChangePasswordResponse


interface SettingsRepository {
  suspend fun  changePassword( changePasswordRequest2: ChangePasswordRequest): UseCaseResult<ChangePasswordResponse>
}

 class  settingsRepoImpl (val hmoAPI: HmoAPI,private val paperPrefs: PaperPrefs):BaseRepository(),SettingsRepository{

     override suspend fun changePassword(changePasswordRequest: ChangePasswordRequest): UseCaseResult<ChangePasswordResponse> {
         return safeApiCall(changePasswordRequest,hmoAPI::changePassword, { response ->response.responsecode == "00"})

     }

 }