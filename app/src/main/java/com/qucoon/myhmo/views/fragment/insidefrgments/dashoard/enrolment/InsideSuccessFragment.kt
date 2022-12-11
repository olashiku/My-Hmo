package com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neptune.utils.argument
import com.qucoon.myhmo.R
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.myhmo.views.activity.OutsideActivity
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_inside_success.*


class InsideSuccessFragment : BaseFragment() {

    val title: String by argument("title")
    val message: String by argument("message")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inside_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClick()
        initView()
    }

    fun initOnClick() {
        createUserButton.setOnClickListener {
            mFragmentNavigation.clearStack()

        }
    }

    fun initView() {
        (activity as MainActivity?)!!.hideToolsBar()
        tvTitle.text = title
        tvMessage.text = message


    }
}