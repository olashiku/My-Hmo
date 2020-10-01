package com.qucoon.myhmo.popups.utilitypupups

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neptune.utils.argument
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.qucoon.myhmo.R
import kotlinx.android.synthetic.main.fragment_single_button_bottom_sheet_dialog.*


class SingleButtonBottomSheetDialogFragment : BottomSheetDialogFragment() {


      interface PerformActionCallBack{
          fun performAction()
      }

    lateinit var performActionCallBack:PerformActionCallBack


    val title:String by argument("title")
    val messg:String by argument("message")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_button_bottom_sheet_dialog, container, false) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
    }

     fun initOnClick(){
         submitButton.setOnClickListener{
             performActionCallBack.performAction()
         }
     }

     fun initView(){
         performActionCallBack = parentFragment as PerformActionCallBack
         tvTitle.setText(title)
         message.setText(messg)
     }
}