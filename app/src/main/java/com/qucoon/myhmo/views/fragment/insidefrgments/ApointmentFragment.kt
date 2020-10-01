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
    }

    fun initView(){

        (activity as MainActivity).showTablayout()


        val list = listOf<HealthDataclasses>(
            HealthDataclasses(R.drawable.userimage,"View My Profile"),
            HealthDataclasses(R.drawable.healthtipsimage,"Read  Health Tips"),
            HealthDataclasses(R.drawable.doctorimage,"Consult a Doctor"),
            HealthDataclasses(R.drawable.secondarycareimage,"Access Secondary Care"),
            HealthDataclasses(R.drawable.hospitallistimage,"View Hospital List"),
            HealthDataclasses(R.drawable.benefitimage,"View Exposure List"))


        HealthRecycler.updateRecycler(context!!, list, R.layout.health_text_layout, listOf(R.id.Name, R.id.image),
            { innerViews, position ->
                val name = innerViews[R.id.Name] as TextView
                val image = innerViews[R.id.image] as ImageView
                name.setText(list[position].name)
                image.setImageResource(list[position].image)

            },
            { position ->
                val item = list[position]
                when(position){
                    0 ->{ }
                    1 ->{ }
                    2 ->{ }
                    3 ->{ }
                    4 ->{ }
                    5 ->{ }

                }
            })

    }


}
