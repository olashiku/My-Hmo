package com.qucoon.myhmo.views.fragment.insidefrgments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.neptune.utils.gone
import com.example.neptune.utils.updateRecycler
import com.google.android.material.switchmaterial.SwitchMaterial
import com.qucoon.myhmo.R
import com.qucoon.myhmo.dataclasses.ProfileDataClass
import com.qucoon.myhmo.dataclasses.SettingsClass
import com.qucoon.myhmo.popups.ContactusDialogFragment
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile2.*


class ProfileFragment :   BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile2, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClick()
        initView()
    }

     fun initView(){

         (activity as MainActivity).hideTablayout()
         planInformationRecyler.setNestedScrollingEnabled(false);
         contactInformationRecycler.setNestedScrollingEnabled(false);
         personalinfoRecycler.setNestedScrollingEnabled(false);

         initpersonalinformaition()
         initContactinformation()
         initplanInformation()

     }

     fun initplanInformation(){


         val list = listOf<ProfileDataClass>(
             ProfileDataClass("Package","Gold"),
             ProfileDataClass("Plan Type","Individual"),
             ProfileDataClass("Duration","Monthly")
             )

         planInformationRecyler.updateRecycler(context!!, list, R.layout.myprofileitemlayout, listOf(R.id.title, R.id.message),
             { innerViews, position ->
                 val title = innerViews[R.id.title] as TextView
                 val message = innerViews[R.id.message] as TextView

                 title.text = list[position].title
                 message.text = list[position].name
             },
             { position ->

             })
     }




     fun initContactinformation(){


         val list = listOf<ProfileDataClass>(
             ProfileDataClass("Address","14 ajasan street off epe"),
             ProfileDataClass("Phone Number (Home)","09023321013"))

         contactInformationRecycler.updateRecycler(context!!, list, R.layout.myprofileitemlayout, listOf(R.id.title, R.id.message),
             { innerViews, position ->
                 val title = innerViews[R.id.title] as TextView
                 val message = innerViews[R.id.message] as TextView

                 title.text = list[position].title
                 message.text = list[position].name
             },
             { position ->

             })


     }

     fun initpersonalinformaition(){

         val list = listOf<ProfileDataClass>(
             ProfileDataClass("Sex","Male"),
             ProfileDataClass("Email","seansharpzy@gmail.com"),
             ProfileDataClass("HMO ID","QCN/10008/A"),
             ProfileDataClass("Date of Birth","1990-04-03"),
             ProfileDataClass("Email","seansharpzy@gmail.com")
             )

         personalinfoRecycler.updateRecycler(context!!, list, R.layout.myprofileitemlayout, listOf(R.id.title, R.id.message),
             { innerViews, position ->
                 val title = innerViews[R.id.title] as TextView
                 val message = innerViews[R.id.message] as TextView

                 title.text = list[position].title
                 message.text = list[position].name
             },
             { position ->

             })

     }

     fun initOnClick(){
         backButtonProfile.setOnClickListener {
             mFragmentNavigation.popFragment()
         }
     }
}