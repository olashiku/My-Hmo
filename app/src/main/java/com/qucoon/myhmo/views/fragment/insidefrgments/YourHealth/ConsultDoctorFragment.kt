package com.qucoon.myhmo.views.fragment.insidefrgments.YourHealth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.neptune.utils.getString
//import com.freshchat.consumer.sdk.Freshchat
//import com.freshchat.consumer.sdk.FreshchatMessage
import com.qucoon.myhmo.R
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.myhmo.views.fragment.insidefrgments.YourHealth.consultation.ConsultConfirmationFragment
import com.qucoon.royalexchange.ui.base.BaseFragment
import com.zoho.livechat.android.ZohoLiveChat
import com.zoho.salesiqembed.ZohoSalesIQ
import kotlinx.android.synthetic.main.fragment_consult_doctor.*


class ConsultDoctorFragment : BaseFragment() {


    var selecedItem = ""

    private val benefList = arrayOf("My self")
    private val symptomsList = arrayOf("Headache", "Back Pains", "Stomach Pain")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consult_doctor, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
        initSLider()
    }

    fun initSLider() {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            context!!,
            android.R.layout.simple_dropdown_item_1line,
            benefList
        )
        consultBeneficiary.setAdapter(adapter)

        val adapter2: ArrayAdapter<String> = ArrayAdapter<String>(
            context!!,
            android.R.layout.simple_dropdown_item_1line,
            symptomsList
        )
        primaryIssue.setAdapter(adapter2)
    }


    fun initOnClick() {

        chatButton.setOnClickListener {
            selecedItem = "chat"
            chatButton.setImageResource(R.drawable.selectedchat)
            callButton.setImageResource(R.drawable.unselectedcall)

        }
        callButton.setOnClickListener {
            selecedItem = "call"
            chatButton.setImageResource(R.drawable.unselectedchat)
            callButton.setImageResource(R.drawable.selectedcall)
        }


        backButtonProfile2.setOnClickListener {
            mFragmentNavigation.popFragment()
        }



        continueButtonBT2.setOnClickListener {

            when (selecedItem) {
                "call" -> {

                    val tag = "call"
                    val msgText =
                        "please call me. i am not feelign too good. thank you! my phone number is ${
                            paperPrefs.getStringPref(PaperPrefs.PHONE)
                        }"

                    mFragmentNavigation.pushFragment(ConsultConfirmationFragment())
                }
                "chat" -> {
                    ZohoLiveChat.Chat.show()
                }
                else -> {
                    showError("Kindly select an option before you proceed.")
                }
            }


        }
    }

    fun initView() {
        (activity as MainActivity).hideTablayout()
    }


}