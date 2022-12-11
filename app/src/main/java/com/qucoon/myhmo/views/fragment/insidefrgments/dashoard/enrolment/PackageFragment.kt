package com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.neptune.utils.withArguments
import com.qucoon.myhmo.R
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_package2.*

class PackageFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_package2, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClick()
        initView()
    }

     fun initView(){
         (activity as MainActivity).hideTablayout()

     }

     fun initOnClick(){

         backButtonPackage.setOnClickListener {
             mFragmentNavigation.popFragment()
         }

         groupButton.setOnClickListener {
             mFragmentNavigation.pushFragment(SubCategoryFragment().withArguments(
                 "subscriptiontype" to "group"
             ))
         }
         individualButton.setOnClickListener{
           mFragmentNavigation.pushFragment(SubCategoryFragment().withArguments(
               "subscriptiontype" to "individual"
           ))
         }
         familyButton.setOnClickListener {
             mFragmentNavigation.pushFragment(SubCategoryFragment().withArguments(
                 "subscriptiontype" to "family"
             ))
         }
         corporateButton.setOnClickListener {
             Toast.makeText(context,"i have been clicked corporate", Toast.LENGTH_SHORT).show()

         }
     }
}