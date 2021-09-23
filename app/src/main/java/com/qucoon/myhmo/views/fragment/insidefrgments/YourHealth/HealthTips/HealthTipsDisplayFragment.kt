package com.qucoon.myhmo.views.fragment.insidefrgments.YourHealth.HealthTips

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.neptune.utils.argument
import com.qucoon.myhmo.R
import com.qucoon.myhmo.views.fragment.insidefrgments.YourHealth.HealthData
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_health_tips_display.*


class HealthTipsDisplayFragment : BaseFragment() {

    val selecteddata: HealthData by argument("HealthData")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_health_tips_display, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClick()
        initView()
    }

     fun initView(){
         txTitle.text = selecteddata.title
         tvDescription.text= selecteddata.description
         Glide.with(this).load(selecteddata.image).into(displayImage);


     }

     fun initOnClick(){
         backButtonHealthTips2.setOnClickListener { mFragmentNavigation.popFragment() }
     }
}