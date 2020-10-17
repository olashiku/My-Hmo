package com.qucoon.myhmo.views.fragment.insidefrgments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.freshchat.consumer.sdk.Freshchat
import com.freshchat.consumer.sdk.FreshchatConfig

import com.qucoon.myhmo.R
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.myhmo.views.fragment.insidefrgments.YourHealth.ConsultDoctorFragment
import com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment.PackageFragment
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
    }
    
     fun initOnClick(){


         profileButton.setOnClickListener {
             mFragmentNavigation.pushFragment(ProfileFragment())

         }

         enrolButton.setOnClickListener {
             mFragmentNavigation.pushFragment(PackageFragment())
         }
         consultDoctorButton.setOnClickListener {
             mFragmentNavigation.pushFragment(ConsultDoctorFragment())

         }

         talkToSuppotButton.setOnClickListener {
             gotoSupportChart()
         }
     }

    fun initView(){
        (activity as MainActivity).showTablayout()
        (activity as MainActivity).hideToolsBar()

    }


    fun gotoSupportChart(){
        val config = FreshchatConfig("3d4f39c6-3189-4605-9d41-db43041b4296", "c31a930b-cb40-4b1e-bf29-4584c4e5b95a")
        config.setDomain("msdk.freshchat.com")
        config.setCameraCaptureEnabled(false);
        config.setGallerySelectionEnabled(false);
        config.setResponseExpectationEnabled(false);

        val freshchatUser = Freshchat.getInstance(context!!).user
        freshchatUser.firstName = paperPrefs.getStringPref(PaperPrefs.FIRSTNAME)
        freshchatUser.lastName =  paperPrefs.getStringPref(PaperPrefs.LASTNAME)
        freshchatUser.email = paperPrefs.getStringPref(PaperPrefs.EMAIL)
        freshchatUser.setPhone("+234",  paperPrefs.getStringPref(PaperPrefs.PHONE))

        Freshchat.getInstance(context!!).setUser(freshchatUser).init(config)

        Freshchat.showConversations(context!!);

    }



}
