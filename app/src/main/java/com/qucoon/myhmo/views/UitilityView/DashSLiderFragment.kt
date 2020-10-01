package com.qucoon.myhmo.views.UitilityView


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qucoon.myhmo.dataclasses.DashboardData

import com.qucoon.myhmo.R
import com.qucoon.royalexchange.utils.argument
import com.qucoon.royalexchange.utils.withArguments
import kotlinx.android.synthetic.main.fragment_dash_slider.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DashSLiderFragment : Fragment() {

    private val onboarddata: DashboardData by argument(DASHBOARDDATA)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dash_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    fun initView(){
        title.setText(onboarddata.text1)
        message.setText(onboarddata.text2)

    }



    companion object {
        val DASHBOARDDATA = "DASHBOARDDATA"
        fun newInstance(onboarddata: DashboardData): DashSLiderFragment{
            return DashSLiderFragment().withArguments(DASHBOARDDATA to onboarddata)
        }
    }

}
