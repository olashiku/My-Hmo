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
import com.example.neptune.utils.argument
import com.example.neptune.utils.withArguments
import com.qucoon.myhmo.R
import com.qucoon.myhmo.viewmodel.OutsideViewModel
import com.qucoon.myhmo.views.activity.OutsideActivity
import com.qucoon.nibbs.utils.Validator
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_new_password.*
import org.koin.android.viewmodel.ext.android.viewModel


class NewPasswordFragment : BaseFragment() {

    val outsideViewModel: OutsideViewModel by viewModel()
    val email:String by argument("email")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClick()
        initViewModel()
        watchFields()

    }

     fun watchFields(){
         etnewPassword1.doAfterTextChanged { Validator.isValidPassword2(etnewPassword1) }
         etnewPassword2.doAfterTextChanged { Validator.isValidPassword2(etnewPassword2) }
     }

     fun initViewModel(){
         setUpObservers(outsideViewModel)

         outsideViewModel.newPasswordResetResponse.observe(viewLifecycleOwner, Observer {
             mFragmentNavigation.pushFragment(SuccessFragment().withArguments(
                 "title" to "Password Reset Successful",
                 "message" to "Your password has been changed successfully, please login to continue."
             ))
         })
     }

     fun initOnClick(){
         pasnextButton.setOnClickListener {
             if( Validator.isValidPassword2(etnewPassword1) &&  Validator.isValidPassword2(etnewPassword2) &&
                 Utils.checkpinmatch(etnewPassword1,etnewPassword2,context!!)){

                 outsideViewModel.newPasswordReset(email, Utils.getTetxt(etnewPassword1))
             }
         }
         backButtonConfirmPassword2.setOnClickListener { mFragmentNavigation.popFragment() }
     }


}