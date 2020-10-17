package com.qucoon.myhmo.views.fragment.insidefrgments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.neptune.utils.gone
import com.example.neptune.utils.updateRecycler
import com.google.android.material.switchmaterial.SwitchMaterial

import com.qucoon.myhmo.R
import com.qucoon.myhmo.dataclasses.HealthDataclasses
import com.qucoon.myhmo.popups.utilitypupups.SignoutBottomSheetDialogFragment
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.myhmo.views.fragment.insidefrgments.YourHealth.*
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_apointment.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ApointmentFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apointment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
    }

     fun initOnClick(){

         myProfileButton.setOnClickListener {
             mFragmentNavigation.pushFragment(ProfileFragment())
         }

         healthtipsButton.setOnClickListener {
             mFragmentNavigation.pushFragment(HealthTipsFragment())
         }

         consultDoctorButtonn.setOnClickListener {
             mFragmentNavigation.pushFragment(ConsultDoctorFragment())
         }

         accessSecondaryCareButton.setOnClickListener {
             mFragmentNavigation.pushFragment(SecondaryCareFragment())
         }

         viewHospitalButton.setOnClickListener {
             mFragmentNavigation.pushFragment(HospitalFragment())
         }

         viewExposirelist.setOnClickListener {
            mFragmentNavigation.pushFragment(ExposirelistFragment())
         }



     }

    fun initView(){

        (activity as MainActivity).showTablayout()




    }


}
