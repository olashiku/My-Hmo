package com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neptune.utils.argument
import com.example.neptune.utils.withArguments
import com.qucoon.myhmo.R
import com.qucoon.myhmo.views.UitilityView.itemview
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_onboard_item.view.*
import kotlinx.android.synthetic.main.fragment_package_category.view.*
import kotlinx.android.synthetic.main.fragment_sub_category.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SubCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SubCategoryFragment : BaseFragment() {

    val subtype:String by argument("subscriptiontype")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sub_category, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClick()
        initView()
    }

     fun initView(){
         (activity as MainActivity).hideTablayout()
         goldPackageButton.setTextView2(subtype.capitalize())
         platinumPackageButton.setTextView2(subtype.capitalize())


     }

     fun initOnClick(){


         backButtonCategory.setOnClickListener {
             mFragmentNavigation.popFragment()
         }

         goldPackageButton.setOnClickListener {
             mFragmentNavigation.pushFragment(CategoryDisplayFragment().withArguments(
                 "type" to "gold",
                 "subtype" to subtype
             ))
         }

         platinumPackageButton.setOnClickListener {
             mFragmentNavigation.pushFragment(CategoryDisplayFragment().withArguments(
                 "type" to "platinum",
                 "subtype" to subtype
             ))
         }
     }
}