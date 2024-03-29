package com.qucoon.myhmo.views.UitilityView


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qucoon.myhmo.dataclasses.OnboardDataClass

import com.qucoon.myhmo.R
import com.qucoon.royalexchange.utils.argument
import com.qucoon.royalexchange.utils.withArguments
import kotlinx.android.synthetic.main.fragment_onboard_item.*

class OnboardItemFragment : Fragment() {

    private val onboarddata: OnboardDataClass by argument(BOARDDATA)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboard_item, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView(){
        baseimage.setImageResource(onboarddata.image)
        text1.setText(onboarddata.text1)
        text2.setText(onboarddata.text2)
        text3.setText(onboarddata.text3)
    }




    companion object {
        val BOARDDATA = "onboarddata"
        fun newInstance(onboarddata: OnboardDataClass): OnboardItemFragment{
            return OnboardItemFragment().withArguments(BOARDDATA to onboarddata)
        }
    }
}
