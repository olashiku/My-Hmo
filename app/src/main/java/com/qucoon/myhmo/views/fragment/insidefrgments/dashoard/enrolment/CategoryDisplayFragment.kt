package com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qucoon.myhmo.R
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_category_display.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryDisplayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryDisplayFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_display, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
    }

     fun initOnClick(){
         backButtonCatType.setOnClickListener {
             mFragmentNavigation.popFragment()
         }
     }

     fun initView(){
         tab.addTab(tab.newTab().setText("Monthly"));
         tab.addTab(tab.newTab().setText("Quaterly"));
         tab.addTab(tab.newTab().setText("Annually"));
     }
}