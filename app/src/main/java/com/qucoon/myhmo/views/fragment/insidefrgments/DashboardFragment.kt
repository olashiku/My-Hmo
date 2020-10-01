package com.qucoon.myhmo.views.fragment.insidefrgments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.qucoon.myhmo.R
import com.qucoon.myhmo.views.activity.MainActivity
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

         enrolButton.setOnClickListener {
             mFragmentNavigation.pushFragment(PackageFragment())
         }
         consultDoctorButton.setOnClickListener {
             Toast.makeText(context,"i have been clicked1", Toast.LENGTH_SHORT).show()
         }

         talkToSuppotButton.setOnClickListener {
             Toast.makeText(context,"i have been clicked2", Toast.LENGTH_SHORT).show()
         }
     }

    fun initView(){
        (activity as MainActivity).showTablayout()
        (activity as MainActivity).hideToolsBar()

    }



}
