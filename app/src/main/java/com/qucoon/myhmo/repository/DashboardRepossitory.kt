package com.qucoon.myhmo.repository

import com.example.neptune.utils.Constants
import com.nubis.watchguard.utils.UseCaseResult
import com.qucoon.myhmo.APIs.HmoAPI
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.model.request.confirmpaystack.PaystackConfirmationRequest
import com.qucoon.myhmo.model.request.enrolmentcomplete.enrolmentcompleteRequest
import com.qucoon.myhmo.model.request.enroluser.EnroluserRequest
import com.qucoon.myhmo.model.request.initpaystack.InitpaymentRequest
import com.qucoon.myhmo.model.response.confirmpaystack.PaystackConfirmationResponse
import com.qucoon.myhmo.model.response.enrolmentcompleteResponse
import com.qucoon.myhmo.model.response.enroluser.EnroluserResponse
import com.qucoon.myhmo.model.response.initpayment.InitpaymentResponse

interface DashboardRepossitory {
   suspend fun initpayment(initpaymentRequest: InitpaymentRequest):UseCaseResult<InitpaymentResponse>
    suspend fun paystackConfirm(paystackConfirmationRequest: PaystackConfirmationRequest): UseCaseResult<PaystackConfirmationResponse>
    suspend fun enrolUser(enroluserRequest: EnroluserRequest):UseCaseResult<EnroluserResponse>
    suspend fun enrolmentcomplete(enrolmentcompleteRequest: enrolmentcompleteRequest):UseCaseResult<enrolmentcompleteResponse>
}


 class implDashboardRepossitory(private val hmoAPI: HmoAPI, private val paperPrefs: PaperPrefs):BaseRepository(),DashboardRepossitory{

     override suspend fun initpayment(initpaymentRequest: InitpaymentRequest): UseCaseResult<InitpaymentResponse> {
         return safeApiCall(initpaymentRequest,hmoAPI::initPaystack, { response ->response.responsecode == Constants.SUCCESS})
     }

     override suspend fun paystackConfirm(paystackConfirmationRequest: PaystackConfirmationRequest): UseCaseResult<PaystackConfirmationResponse> {
         return safeApiCall(paystackConfirmationRequest,hmoAPI::confirmpayment, { response ->response.responsecode == Constants.SUCCESS})
     }

     override suspend fun enrolUser(enroluserRequest: EnroluserRequest): UseCaseResult<EnroluserResponse> {
         return safeApiCall(enroluserRequest,hmoAPI::enrolusers, { response ->response.responsecode == Constants.SUCCESS})

     }

     override suspend fun enrolmentcomplete(enrolmentcompleteRequest: enrolmentcompleteRequest): UseCaseResult<enrolmentcompleteResponse> {
         return safeApiCall(enrolmentcompleteRequest,hmoAPI::enrolmentcomplete, { response ->response.responsecode == Constants.SUCCESS})
     }
 }