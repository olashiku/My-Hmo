package com.qucoon.myhmo.views.fragment.insidefrgments.YourHealth.consultation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qucoon.myhmo.R
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_consult_confirmation.*


class ConsultConfirmationFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consult_confirmation, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClick()
    }

     fun initOnClick(){
         goBackbutton.setOnClickListener { mFragmentNavigation.clearStack() }
     }


}