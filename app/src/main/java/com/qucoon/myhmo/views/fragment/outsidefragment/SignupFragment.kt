package com.qucoon.myhmo.views.fragment.outsidefragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.neptune.utils.Utils
import com.example.neptune.utils.withArguments
import com.qucoon.myhmo.R
import com.qucoon.myhmo.views.activity.OutsideActivity
import com.qucoon.nibbs.utils.Validator
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_signup.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SignupFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
        watchInputFields()

    }

     fun watchInputFields(){
         etFirstName.doAfterTextChanged { Validator.isValidFirstName(etFirstName)}
         etLastName.doAfterTextChanged { Validator.isValidLastName(etLastName)}
         etEmail.doAfterTextChanged { Validator.isValidEmail(etEmail)}
         etPhoneno.doAfterTextChanged { Validator.isValidPhone(etPhoneno)}

     }

     fun initOnClick(){
         signupSumitButton.setOnClickListener{

             if(Validator.isValidPhone(etPhoneno) && Validator.isValidEmail(etEmail)
                 && Validator.isValidFirstName(etLastName) && Validator.isValidFirstName(etFirstName)){

                 mFragmentNavigation.pushFragment(PasswordFragment().withArguments(
                     "firstname" to Utils.getTetxt(etFirstName),
                     "lastname" to Utils.getTetxt(etLastName),
                     "etEmail" to Utils.getTetxt(etEmail),
                     "etPhoneno" to Utils.getTetxt(etPhoneno)))

             }

         }

         backButton.setOnClickListener{
             mFragmentNavigation.popFragment()
         }

     }

     fun initView(){
      //   (activity as OutsideActivity?)!!.showToolsbarAndSetTitle("Personal Details")
     }
}
