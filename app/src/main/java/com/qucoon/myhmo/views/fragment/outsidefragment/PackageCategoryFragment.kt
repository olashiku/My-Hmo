package com.qucoon.myhmo.views.fragment.outsidefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qucoon.myhmo.R
import com.qucoon.myhmo.views.activity.OutsideActivity
import com.qucoon.royalexchange.ui.base.BaseFragment
import com.qucoon.royalexchange.utils.argument
import kotlinx.android.synthetic.main.fragment_package_category.*


class PackageCategoryFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_package_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
    }

     fun initView(){
         (activity as OutsideActivity?)!!.showToolsbarAndSetTitle("Select Package")
     }

     fun initOnClick(){
         itemview2.setOnClickListener {

         }

         itemview.setOnClickListener {  }

         subbutton.setOnClickListener {
             mFragmentNavigation.pushFragment(SuccessFragment())
         }
     }
}