package com.qucoon.myhmo.views.fragment.outsidefragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neptune.utils.Utils

import com.qucoon.myhmo.R
import com.qucoon.royalexchange.ui.base.BaseFragment


class SplashFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       initiew()
    }

    fun initiew(){
        Utils.delaytime(2000) { gotoNextActivity() }
    }


    fun gotoNextActivity(){
       mFragmentNavigation.pushFragment(OnboardFragment())
    }


}
