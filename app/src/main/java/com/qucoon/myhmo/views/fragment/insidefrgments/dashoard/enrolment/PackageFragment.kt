package com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.qucoon.myhmo.R
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_package2.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PackageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PackageFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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
         individualButton.setOnClickListener{
           mFragmentNavigation.pushFragment(SubCategoryFragment())
         }
         familyButton.setOnClickListener {
             mFragmentNavigation.pushFragment(SubCategoryFragment())
         }
         corporateButton.setOnClickListener {
             Toast.makeText(context,"i have been clicked corporate", Toast.LENGTH_SHORT).show()

         }
     }
}