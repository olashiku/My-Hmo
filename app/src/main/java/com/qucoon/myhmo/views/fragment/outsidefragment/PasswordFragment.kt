package com.qucoon.myhmo.views.fragment.outsidefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.example.neptune.utils.Utils
import com.google.firebase.crashlytics.internal.common.CommonUtils
import com.qucoon.myhmo.R
import com.qucoon.myhmo.viewmodel.OutsideViewModel
import com.qucoon.myhmo.views.activity.OutsideActivity
import com.qucoon.nibbs.utils.Validator
import com.qucoon.royalexchange.ui.base.BaseFragment
import com.qucoon.royalexchange.utils.argument
import com.qucoon.royalexchange.utils.withArguments
import kotlinx.android.synthetic.main.fragment_password.*
import kotlinx.android.synthetic.main.fragment_signup.*
import org.koin.android.viewmodel.ext.android.viewModel


class PasswordFragment : BaseFragment() {

    val firstname :String by argument("firstname")
    val lastname :String by argument("lastname")
    val etEmail :String by argument("etEmail")
    val etPhoneno :String by argument("etPhoneno")


    val outsideViewModel: OutsideViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initviewModel()
        initView()
        initonClick()
        watchPassword()
    }

     fun initviewModel(){
         setUpObservers(outsideViewModel)
     }

     fun watchPassword(){
         etPassword1.doAfterTextChanged { Validator.isValidPassword2(etPassword1)}
         etPassword2.doAfterTextChanged { Validator.isValidPassword2(etPassword2)}

     }




     fun initonClick(){
         passwordNextButton.setOnClickListener{

             if(Validator.isValidPassword2(etPassword1) && Validator.isValidPassword2(etPassword2)
                 && Utils.checkpinmatch(etPassword1,etPassword1,context!!)){
                 generateAuthToken()
             }

         }

         backButtonConfirmPassword.setOnClickListener { mFragmentNavigation.popFragment() }
     }

     fun generateAuthToken(){

         outsideViewModel.authEmail(etEmail)
         outsideViewModel.authemailResponse.observe(viewLifecycleOwner, Observer {
             mFragmentNavigation.pushFragment(PackageFragment().withArguments(
                 "firstname" to firstname,
                 "lastname" to lastname,
                 "etEmail" to etEmail,
                 "etPhoneno" to etPhoneno,
                 "password" to Utils.getTetxt(etPassword2),
                 "uniqueref" to it.uniqueref
             ))
         })


     }

    fun initView(){
      // (activity as OutsideActivity?)!!.showToolsbarAndSetTitle("Create Password")
    }

}