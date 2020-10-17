package com.qucoon.myhmo.popups

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
import kotlinx.android.synthetic.main.fragment_automate_request_dialog.*


class AutomateRequestDialogFragment : DialogFragment() {

    interface AutomateDialogCallback{
        fun automateStatus(value:String)
    }

    lateinit var automateDialogCallback:AutomateDialogCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_automate_request_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
    }

     fun initOnClick(){
         ok.setOnClickListener {
             dismiss()
             automateDialogCallback.automateStatus("yes")

         }

         ok2.setOnClickListener {
             dismiss()
             automateDialogCallback.automateStatus("no")

         }
     }

     fun initView(){

         automateDialogCallback = parentFragment as AutomateDialogCallback

         if (dialog != null && getDialog()!!.window != null) {
             dialog!!.window!!.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
             dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE);
         }
     }
}