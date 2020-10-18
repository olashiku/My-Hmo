package com.qucoon.myhmo.model.request.enroluser

import com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment.BeneficiaryDetails
import java.io.Serializable

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
    val user_package: String,
    val subscriber_info: List<BeneficiaryDetails>
):Serializable