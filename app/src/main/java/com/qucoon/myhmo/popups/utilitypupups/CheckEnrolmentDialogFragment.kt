package com.qucoon.myhmo.popups.utilitypupups

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.qucoon.myhmo.R
import kotlinx.android.synthetic.main.fragment_check_enrolment_dialog.*


class CheckEnrolmentDialogFragment : DialogFragment() {

    interface EnrolmentCallback{
        fun EnrolmentStatus(value:Boolean)
    }

    lateinit var enrolmentCallback: EnrolmentCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_enrolment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enrolmentCallback = parentFragment as EnrolmentCallback
        initOnClick()
        initView()
    }

     fun initView(){
        if (dialog != null && getDialog()!!.window != null) {
            dialog!!.window!!.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
            dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE);
        }
    }

     fun initOnClick(){
         continueButton.setOnClickListener {
             dismiss()
             enrolmentCallback.EnrolmentStatus(true)
         }

         cancelbutton.setOnClickListener {
             dismiss()
             enrolmentCallback.EnrolmentStatus(false)
         }
     }
}