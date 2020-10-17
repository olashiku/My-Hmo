package com.qucoon.myhmo.views.fragment.insidefrgments.settings

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
import com.qucoon.myhmo.popups.utilitypupups.SingleButtonBottomSheetDialogFragment
import com.qucoon.myhmo.viewmodel.SettingsViewModel
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.nibbs.utils.Validator
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_change_password.*
import kotlinx.android.synthetic.main.fragment_signup.*
import org.koin.android.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChangePasswordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChangePasswordFragment : BaseFragment(),SingleButtonBottomSheetDialogFragment.PerformActionCallBack{

    val settingsViewModel: SettingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
        initOnClick()
    }

    fun initOnClick(){

        backButtonChangePassword.setOnClickListener {
            mFragmentNavigation.popFragment()
        }

        changePasswordSumitButton.setOnClickListener {
            if(Validator.isValidPassword2(etOldPassword) &&  Validator.isValidPassword2(etNewPassword1)
                && Validator.isValidPassword2(etNewPassword2) && Utils.checkpinmatch(etNewPassword1,etNewPassword2,context!!)){
                settingsViewModel.changePassword(Utils.getTetxt(etNewPassword1),Utils.getTetxt(etOldPassword))
            }else {
                showError("Kindly input values in the required field")
            }
        }


    }


     fun initViewModel(){
         setUpObservers(settingsViewModel)

         settingsViewModel.changePasswordResponse.observe(viewLifecycleOwner, Observer {
                mFragmentNavigation.openBottomSheet(SingleButtonBottomSheetDialogFragment().withArguments(
                    "title" to "Successful",
                    "message" to "You have successfully change your password"
                    ))
         })

     }

     fun initView(){
         (activity as MainActivity).showToolsbarAndSetTitle("Change Password")
         (activity as MainActivity).hideTablayout()

         etOldPassword.doAfterTextChanged { Validator.isValidPassword2(etOldPassword)}
         etNewPassword1.doAfterTextChanged{ Validator.isValidPassword2(etNewPassword1)}
         etNewPassword2.doAfterTextChanged{ Validator.isValidPassword2(etNewPassword2)}
     }

    override fun performAction() {
        mFragmentNavigation.popFragment()
    }
}