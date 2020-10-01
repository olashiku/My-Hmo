package com.qucoon.myhmo.views.fragment.outsidefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.neptune.utils.Utils
import com.mukesh.OnOtpCompletionListener
import com.qucoon.myhmo.R
import com.qucoon.myhmo.viewmodel.OutsideViewModel
import com.qucoon.myhmo.views.activity.OutsideActivity
import com.qucoon.royalexchange.ui.base.BaseFragment
import com.qucoon.royalexchange.utils.argument
import com.qucoon.royalexchange.utils.withArguments
import kotlinx.android.synthetic.main.fragment_package.*
import kotlinx.android.synthetic.main.fragment_password.*
import org.koin.android.viewmodel.ext.android.viewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PackageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PackageFragment : BaseFragment() {

    val firstname :String by argument("firstname")
    val lastname :String by argument("lastname")
    val etEmail :String by argument("etEmail")
    val etPhoneno :String by argument("etPhoneno")
    val password :String by argument("password")
    val uniqueref: String by argument("uniqueref")

     val outsideViewModel:OutsideViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_package, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initView()
        initOnClick()
    }

     fun initObserver(){
         setUpObservers(outsideViewModel)
     }

    fun initView(){
      //  (activity as OutsideActivity?)!!.showToolsbarAndSetTitle("Verification Code")

        otp_view.setOtpCompletionListener {
            Log.v("PackageFragment","View Completed")
        }


    }
     fun initOnClick(){
         passwordNextButtonOTP.setOnClickListener{
             if(otp_view.length().equals(6)){
            verifyEmail()
             }else {
                 showError("Insert OTP sent to your email")
             }
         }

         backButtonOTP.setOnClickListener { mFragmentNavigation.popFragment() }
     }

     fun verifyEmail(){
         outsideViewModel.verifyEmail(etEmail,otp_view.text.toString(),uniqueref)
         outsideViewModel.defaultResponse.observe(viewLifecycleOwner, Observer {

             registerUser()


         })
     }

     fun registerUser(){
         outsideViewModel.registerUser(category = "0",email = etEmail,firstname = firstname,lastname = lastname,password = password,phone = etPhoneno)
         outsideViewModel.registerResponse.observe(viewLifecycleOwner, Observer {
         mFragmentNavigation.pushFragment(SuccessFragment().withArguments(
             "title" to "Account Created Successfully",
             "message" to "Your account has been successfully created, please click continue to login to your account."
         ))
         })
     }
}