package com.qucoon.myhmo.views.fragment.insidefrgments.YourHealth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.neptune.utils.updateRecycler
import com.qucoon.myhmo.R
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_hospital2.*
import kotlinx.android.synthetic.main.fragment_profile2.*
import kotlinx.android.synthetic.main.fragment_profile2.planInformationRecyler


class HospitalFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hospital2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
    }


    fun initOnClick() {
        BackButtonHospital.setOnClickListener { mFragmentNavigation.popFragment() }
    }

    fun initView() {
        (activity as MainActivity).hideTablayout()

        val list = listOf<HospitalData>(
            HospitalData("Raddison Blue Hospital", "12 broad street lagos ", "Lagos"),
            HospitalData("Abbot Clinic", "1 apapa road lagos ", "Lagos"),
            HospitalData("Kelin heights Hospotal", "3 masha road lagos ", "Lagos"),
            HospitalData("Irish Hospital lagos", "13 asajon street, sangotedo lagos", "Lagos"),
            HospitalData("Kelin heights Hospotal", "3 masha road lagos ", "Lagos"),
            HospitalData("Irish Hospital lagos", "13 asajon street, sangotedo lagos", "Lagos"),
            HospitalData("Kelin heights Hospotal", "3 masha road lagos ", "Lagos"),
            HospitalData("Irish Hospital lagos", "13 asajon street, sangotedo lagos", "Lagos"),
            HospitalData("Kelin heights Hospotal", "3 masha road lagos ", "Lagos"),
            HospitalData("Irish Hospital lagos", "13 asajon street, sangotedo lagos", "Lagos"),
            HospitalData("Kelin heights Hospotal", "3 masha road lagos ", "Lagos"),
            HospitalData("Irish Hospital lagos", "13 asajon street, sangotedo lagos", "Lagos"),
            HospitalData("Kelin heights Hospotal", "3 masha road lagos ", "Lagos"),
            HospitalData("Irish Hospital lagos", "13 asajon street, sangotedo lagos", "Lagos"),
            HospitalData("Kelin heights Hospotal", "3 masha road lagos ", "Lagos"),
            HospitalData("Irish Hospital lagos", "13 asajon street, sangotedo lagos", "Lagos"),
            HospitalData("Kelin heights Hospotal", "3 masha road lagos ", "Lagos"),
            HospitalData("Irish Hospital lagos", "13 asajon street, sangotedo lagos", "Lagos"),
            HospitalData("Appitos  Specialist Hospital", "98 karimu street lagos", "Lagos")
        )

        updateRecycler(list)
    }


    fun updateRecycler(list: List<HospitalData>) {

        hospitalRecycler.updateRecycler(context!!,
            list,
            R.layout.hospital_layout,
            listOf(R.id.hospitalName, R.id.hospitalAddress),
            { innerViews, position ->
                val hospitalName = innerViews[R.id.hospitalName] as TextView
                val hospitalAddress = innerViews[R.id.hospitalAddress] as TextView

                hospitalName.text = list[position].hospitalName
                hospitalAddress.text = list[position].hospitalAddress
            },
            { position ->

            })

    }
}

data class HospitalData(
    var hospitalName: String,
    var hospitalAddress: String,
    var hospitalLocation: String
)