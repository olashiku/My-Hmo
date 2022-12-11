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
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.myhmo.popups.utilitypupups.CheckEnrolmentDialogFragment
import com.qucoon.myhmo.popups.utilitypupups.SignoutBottomSheetDialogFragment
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.myhmo.views.fragment.insidefrgments.YourHealth.*
import com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment.PackageFragment
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_apointment.*


class ApointmentFragment : BaseFragment(), CheckEnrolmentDialogFragment.EnrolmentCallback {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_apointment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
    }

    fun initOnClick() {

        myProfileButton.setOnClickListener {
            if (paperPrefs.getStringPref(PaperPrefs.ENROLSTATUS).equals("N")) {
                mFragmentNavigation.openDialogFragment(CheckEnrolmentDialogFragment())
            } else {
                mFragmentNavigation.pushFragment(ProfileFragment())
            }
        }


        healthtipsButton.setOnClickListener {
            if (paperPrefs.getStringPref(PaperPrefs.ENROLSTATUS).equals("N")) {
                mFragmentNavigation.openDialogFragment(CheckEnrolmentDialogFragment())
            } else {
                mFragmentNavigation.pushFragment(HealthTipsFragment())
            }


        }

        consultDoctorButtonn.setOnClickListener {

            if (paperPrefs.getStringPref(PaperPrefs.ENROLSTATUS).equals("N")) {
                mFragmentNavigation.openDialogFragment(CheckEnrolmentDialogFragment())
            } else {
                mFragmentNavigation.pushFragment(ConsultDoctorFragment())
            }


        }

        accessSecondaryCareButton.setOnClickListener {

            if (paperPrefs.getStringPref(PaperPrefs.ENROLSTATUS).equals("N")) {
                mFragmentNavigation.openDialogFragment(CheckEnrolmentDialogFragment())
            } else {
                //   mFragmentNavigation.pushFragment(SecondaryCareFragment())
                showError("feature will be available soon.")

            }


        }

        viewHospitalButton.setOnClickListener {
            if (paperPrefs.getStringPref(PaperPrefs.ENROLSTATUS).equals("N")) {
                mFragmentNavigation.openDialogFragment(CheckEnrolmentDialogFragment())
            } else {
                mFragmentNavigation.pushFragment(HospitalFragment())
            }
        }

        viewExposirelist.setOnClickListener {
            if (paperPrefs.getStringPref(PaperPrefs.ENROLSTATUS).equals("N")) {
                mFragmentNavigation.openDialogFragment(CheckEnrolmentDialogFragment())
            } else {
                mFragmentNavigation.pushFragment(ExposirelistFragment())
            }
        }
    }


    fun initView() {
        (activity as MainActivity).showTablayout()
    }

    override fun EnrolmentStatus(value: Boolean) {
        when (value) {
            true -> {
                mFragmentNavigation.pushFragment(PackageFragment())
            }
            false -> {
                showError("You have to enrol before you can perform any activity on MYHMO")
            }
        }
    }

}
