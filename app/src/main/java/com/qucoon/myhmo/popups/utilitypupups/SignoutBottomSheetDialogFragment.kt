package com.qucoon.myhmo.popups.utilitypupups

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.qucoon.myhmo.R
import kotlinx.android.synthetic.main.fragment_signout_bottom_sheet_dialog.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignoutBottomSheetDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignoutBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signout_bottom_sheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      initOnCLick()
    }

     fun initOnCLick(){
         submitButton.setOnClickListener {
             activity!!.finish()
         }

         submitButton2.setOnClickListener {
             dismiss()
         }

     }
}