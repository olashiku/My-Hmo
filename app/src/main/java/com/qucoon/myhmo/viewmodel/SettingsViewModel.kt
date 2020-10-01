package com.qucoon.myhmo.viewmodel

import com.example.neptune.viewmodel.BaseViewModel
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.myhmo.model.request.changepassword.ChangePasswordRequest
import com.qucoon.myhmo.model.response.changepassword.ChangePasswordResponse
import com.qucoon.myhmo.repository.SettingsRepository
import com.qucoon.nibbs.utils.SingleLiveEvent


class SettingsViewModel(val settingsRepository: SettingsRepository, val paperPrefs: PaperPrefs):BaseViewModel() {
    val changePasswordResponse =SingleLiveEvent<ChangePasswordResponse>()

    fun changePassword(newpassword:String, oldPassword:String){
        val request = ChangePasswordRequest(paperPrefs.getStringPref(PaperPrefs.EMAIL), newpassword,oldPassword)
        apiRequest(request,settingsRepository::changePassword,changePasswordResponse,{it.responsemessage})
    }


}