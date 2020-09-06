package com.qucoon.myhmo.views.fragment.outsidefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.neptune.utils.argument
import com.example.neptune.utils.withArguments
import com.qucoon.myhmo.R
import com.qucoon.myhmo.viewmodel.OutsideViewModel
import com.qucoon.myhmo.views.activity.OutsideActivity
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_package.*
import kotlinx.android.synthetic.main.fragment_validate_o_t_p.*
import org.koin.android.viewmodel.ext.android.viewModel


class ValidateOTPFragment : BaseFragment() {

    val outsideViewModel: OutsideViewModel by viewModel()

    val uniqueref: String by argument("uniqueref")
    val email:String by argument("email")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_validate_o_t_p, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
        initViewModel()
    }


    fun initViewModel(){
        setUpObservers(outsideViewModel)
        outsideViewModel.authenticatePasswordResponse.observe(viewLifecycleOwner, Observer {
            mFragmentNavigation.pushFragment(NewPasswordFragment().withArguments(
                "email" to email
            ))
        })
    }

    fun initView(){
        (activity as OutsideActivity?)!!.showToolsbarAndSetTitle("Verification Code")
    }
    fun initOnClick(){
        validOtpNextButton.setOnClickListener{

            if(otp_view2.length().equals(6)){
                authPassword()
            }else {
                showError("Insert OTP sent to your email")
            }



        }
    }

     fun authPassword(){
         outsideViewModel.authenticatePassword(email = email,uniqueref = uniqueref,token =  otp_view2.text.toString())
     }

}