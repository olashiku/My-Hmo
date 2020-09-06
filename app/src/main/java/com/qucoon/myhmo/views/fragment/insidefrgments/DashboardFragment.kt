package com.qucoon.myhmo.views.fragment.insidefrgments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.qucoon.myhmo.dataclasses.DashboardData
import com.qucoon.myhmo.dataclasses.MenuData

import com.qucoon.myhmo.R
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.myhmo.views.activity.OutsideActivity
import com.qucoon.myhmo.views.fragment.UitilityView.DashSLiderFragment
import com.qucoon.royalexchange.ui.base.BaseFragment
import com.qucoon.royalexchange.utils.ViewPagerObject
import com.qucoon.royalexchange.utils.setUpViewPager
import com.qucoon.royalexchange.utils.updateRecycler
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
    }

    fun initView(){

    }



}
