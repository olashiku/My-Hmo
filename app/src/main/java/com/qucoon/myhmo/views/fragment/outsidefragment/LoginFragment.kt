package com.qucoon.myhmo.views.fragment.outsidefragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged

import com.example.neptune.utils.afterTextChanged
import com.example.neptune.utils.getString
import com.example.neptune.viewmodel.observeChange
import com.google.firebase.crashlytics.internal.common.CommonUtils

import com.qucoon.myhmo.R
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.myhmo.popups.utilitypupups.SignoutBottomSheetDialogFragment
import com.qucoon.myhmo.popups.utilitypupups.SingleButtonBottomSheetDialogFragment
import com.qucoon.myhmo.viewmodel.OutsideViewModel
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.myhmo.views.activity.OutsideActivity
import com.qucoon.nibbs.utils.Validator
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel



class LoginFragment : BaseFragment() {

     val outsideViewModel: OutsideViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
    }

     fun initView(){
         setUpObservers(outsideViewModel)
         (activity as  OutsideActivity).hideToolsBar()
         etFirstAgentName.setText(paperPrefs.getStringPref(PaperPrefs.EMAIL))
         watchTexts()
     }

     fun watchTexts(){
              etFirstAgentName.doAfterTextChanged { Validator.isValidEmail(etFirstAgentName)}
              etPassword.doAfterTextChanged { Validator.isValidPassword2(etPassword)}
     }

    fun initOnClick(){

        forgotpasswordButton.setOnClickListener {
           mFragmentNavigation.pushFragment(ForgotPasswordFragment())
        }

        submitButton.setOnClickListener {

            if(Validator.isValidEmail(etFirstAgentName) && Validator.isValidPassword2(etPassword)){
                outsideViewModel.loginUser(etPassword.getString(),etFirstAgentName.getString())
                outsideViewModel.loginResponse.observeChange(viewLifecycleOwner){

                    val myIntent = Intent(activity, MainActivity::class.java)
                    startActivity(myIntent)
                }
            } else {
                showError("Invalid Details")
            }
        }


           createUserButton.setOnClickListener {
            mFragmentNavigation.pushFragment(SignupFragment())
        }
    }
}
