package com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.example.neptune.utils.argument
import com.example.neptune.utils.getString
import com.example.neptune.utils.withArguments
import com.qucoon.myhmo.R
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.myhmo.database.savePref
import com.qucoon.myhmo.livedata.DataPasserLiveData
import com.qucoon.myhmo.model.response.enrolmentcompleteResponse
import com.qucoon.myhmo.popups.utilitypupups.DatePickerBottomSheetFragment
import com.qucoon.myhmo.viewmodel.DashboardViewModel
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_complete_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent


class CompleteProfileFragment : BaseFragment(),DatePickerBottomSheetFragment.OnDateSelected {


    val imageurl:String by argument("imageurl")
    private val dataPasserLiveData = KoinJavaComponent.inject(DataPasserLiveData::class.java)


    val dashboardViewModel: DashboardViewModel by viewModel()

    private val Gender = arrayOf("Male", "Female")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_complete_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
        initSpinner()
    }

     fun initSpinner(){
         val adapter: ArrayAdapter<String> = ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, Gender)
         genderspinner.setAdapter(adapter)
     }

     fun initView(){
         setUpObservers(dashboardViewModel)

         dashboardViewModel.enrolmentcompleteResponse.observe(viewLifecycleOwner, Observer {

             dataPasserLiveData.value.hasEnroledStatus.postValue("Y")
             paperPrefs.savePref(PaperPrefs.ENROLSTATUS,"Y")


             mFragmentNavigation.pushFragment(InsideSuccessFragment().withArguments(
                        "title" to "Successful",
                        "message" to "You have successfully enroled on MYHMO, Please check ${paperPrefs.getStringPref(
                            PaperPrefs.EMAIL)} to validate your subscription." + " You can now contact the doctor on MYHMO."))
         })

     }

     fun initOnClick(){
         dobEdittext.setOnClickListener{
             mFragmentNavigation.openBottomSheet(DatePickerBottomSheetFragment.newInstance("Select Date of Birth","Select Date"))
         }


         continueButtonBT.setOnClickListener {
             if(genderspinner.getString().isNotEmpty() && address.getString().isNotEmpty() && dobEdittext.getString().isNotEmpty()){
                 dashboardViewModel.enrolmentcomplete(address.getString(),dobEdittext.getString(),genderspinner.getString(),imageurl)
             }else {
                 showError("Please fill the form with the right values")
             }

         }
     }

    override fun onDatePicked(date: String?) {
        dobEdittext.setText(date)
    }

}