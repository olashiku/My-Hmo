package com.qucoon.myhmo.views.fragment.outsidefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.example.neptune.utils.Utils
import com.example.neptune.utils.withArguments
import com.qucoon.myhmo.R
import com.qucoon.myhmo.viewmodel.OutsideViewModel
import com.qucoon.myhmo.views.activity.OutsideActivity
import com.qucoon.nibbs.utils.Validator
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_forgot_password.*
import kotlinx.android.synthetic.main.fragment_password.*
import org.koin.android.viewmodel.ext.android.viewModel



class ForgotPasswordFragment : BaseFragment() {

    val outsideViewModel: OutsideViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initonClick()
        initViewModel()
        watchView()
    }

    fun watchView(){
        etForgetEmail.doAfterTextChanged { Validator.isValidEmail(etForgetEmail) }
    }

     fun initViewModel(){
         setUpObservers(outsideViewModel)
         outsideViewModel.resetPasswordResponse.observe(viewLifecycleOwner, Observer {
             mFragmentNavigation.pushFragment(ValidateOTPFragment().withArguments(
                 "uniqueref" to it.uniqueref,
                 "email" to Utils.getTetxt(etForgetEmail)
             ))
         })
     }


    fun initonClick(){

        subButtonForgotPassword.setOnClickListener{

            if(Validator.isValidEmail(etForgetEmail)){
                outsideViewModel.resetPassword(Utils.getTetxt(etForgetEmail))
            }

        }

        backButtonForgotPassword.setOnClickListener {
            mFragmentNavigation.popFragment()
        }
    }

    fun initView(){
      //  (activity as OutsideActivity?)!!.showToolsbarAndSetTitle("Reset Password")
    }

}