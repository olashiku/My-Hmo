package com.qucoon.myhmo.views.fragment.insidefrgments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.freshchat.consumer.sdk.Freshchat

import com.qucoon.myhmo.R
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.myhmo.livedata.DataPasserLiveData
import com.qucoon.myhmo.popups.utilitypupups.CheckEnrolmentDialogFragment
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.myhmo.views.fragment.insidefrgments.YourHealth.ConsultDoctorFragment
import com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment.PackageFragment
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.koin.java.KoinJavaComponent


class DashboardFragment : BaseFragment(),CheckEnrolmentDialogFragment.EnrolmentCallback {

    private val dataPasserLiveData = KoinJavaComponent.inject(DataPasserLiveData::class.java)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
        initLiveData()
    }


     fun initLiveData(){
         dataPasserLiveData.value.hasEnroledStatus.observe(viewLifecycleOwner, Observer {
             if(it.equals("Y")){
                 dashCardView.visibility = View.VISIBLE
                 enroledCardView.visibility = View.INVISIBLE
             }
         })
     }
    
     fun initOnClick(){

         renewButton.setOnClickListener {
             Toast.makeText(context,"This feature will be available soon", Toast.LENGTH_SHORT).show()
         }

         btUpgrade.setOnClickListener {
             Toast.makeText(context,"This feature will be available soon", Toast.LENGTH_SHORT).show()
         }


         profileButton.setOnClickListener {

             if(paperPrefs.getStringPref(PaperPrefs.ENROLSTATUS).equals("N")){
                 mFragmentNavigation.openDialogFragment(CheckEnrolmentDialogFragment())
             } else {
                 mFragmentNavigation.pushFragment(ProfileFragment())
             //    mFragmentNavigation.pushFragment(CompleteProfileFragment())

             }



         }

         btRenew.setOnClickListener {
             mFragmentNavigation.pushFragment(PackageFragment())
         }
         consultDoctorButton.setOnClickListener {

             if(paperPrefs.getStringPref(PaperPrefs.ENROLSTATUS).equals("N")){
                 mFragmentNavigation.openDialogFragment(CheckEnrolmentDialogFragment())
             } else {
                 mFragmentNavigation.pushFragment(ConsultDoctorFragment())
             }




         }

         talkToSuppotButton.setOnClickListener {


             if(paperPrefs.getStringPref(PaperPrefs.ENROLSTATUS).equals("N")){
                 mFragmentNavigation.openDialogFragment(CheckEnrolmentDialogFragment())
             } else {
                 gotoSupportChart()
             }

         }
     }

    fun initView(){
        (activity as MainActivity).showTablayout()
        (activity as MainActivity).hideToolsBar()

        usernamePersonTV.setText(paperPrefs.getStringPref(PaperPrefs.FIRSTNAME))


        if(paperPrefs.getStringPref(PaperPrefs.ENROLSTATUS).equals("Y")){

            dashCardView.visibility = View.VISIBLE
            enroledCardView.visibility = View.INVISIBLE
        } else {
            dashCardView.visibility = View.INVISIBLE
            enroledCardView.visibility = View.VISIBLE
        }

         if(paperPrefs.getStringPref(PaperPrefs.IMAGE).isNotEmpty()){
             Glide.with(this).load(paperPrefs.getStringPref(PaperPrefs.IMAGE)).into(circularImageView);
         }

        println("what i am expecting "+ paperPrefs.getStringPref(PaperPrefs.DURATION) +" - " +  paperPrefs.getStringPref(PaperPrefs.DAYSLEFT) +" days left")

        tvPackageName.text= paperPrefs.getStringPref(PaperPrefs.SUBSCRBEDPACKAGE).capitalize()
        tvPackageDuration.text = paperPrefs.getStringPref(PaperPrefs.DURATION) +" - " +  paperPrefs.getStringPref(PaperPrefs.DAYSLEFT) +" days left"
        if(paperPrefs.getStringPref(PaperPrefs.SUBSCRBEDPACKAGE).equals("gold")){
            ivPackageType.setImageResource(R.drawable.golddashimage)
        } else if(paperPrefs.getStringPref(PaperPrefs.SUBSCRBEDPACKAGE).equals("silver")){
            ivPackageType.setImageResource(R.drawable.silverdashimage)
        }else if(paperPrefs.getStringPref(PaperPrefs.SUBSCRBEDPACKAGE).equals("platinum")){
            ivPackageType.setImageResource(R.drawable.platinumdashimage)
        }


    }


    fun gotoSupportChart(){

        Freshchat.showConversations(context!!);

    }

    override fun EnrolmentStatus(value: Boolean) {
        when(value){
            true->{mFragmentNavigation.pushFragment(PackageFragment())}
            false->{
                showError("You have to enrol before you can perform any activity on MYHMO")
            }
        }
    }


}
