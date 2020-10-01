package com.qucoon.myhmo.views.fragment.insidefrgments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.neptune.utils.gone
import com.example.neptune.utils.updateRecycler
import com.freshchat.consumer.sdk.Freshchat
import com.freshchat.consumer.sdk.FreshchatConfig
import com.google.android.material.switchmaterial.SwitchMaterial

import com.qucoon.myhmo.R
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.myhmo.dataclasses.SettingsClass
import com.qucoon.myhmo.popups.ContactusDialogFragment
import com.qucoon.myhmo.popups.utilitypupups.SignoutBottomSheetDialogFragment
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.myhmo.views.fragment.insidefrgments.settings.ChangePasswordFragment
import com.qucoon.myhmo.views.fragment.insidefrgments.settings.ManageAccountFragment
import com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.EnrolUserFragment
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SettingsFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

     fun initView(){


         (activity as MainActivity).showTablayout()
         (activity as MainActivity).setActoonBarTitle("Settings")



         val list1 = listOf<SettingsClass>(
             SettingsClass("Manage Account"),
             SettingsClass("Change Password"),
             SettingsClass("Log Out")
         )

         AccoutSettingRecycler.updateRecycler(context!!, list1, R.layout.settings_text_layout, listOf(R.id.Name, R.id.itemSwitch),
             { innerViews, position ->
                 val name = innerViews[R.id.Name] as TextView
                 val switch = innerViews[R.id.itemSwitch] as SwitchMaterial
                 switch.gone()

                 name.setText(list1[position].name)
             },
             { position ->
                 val item = list1[position]
                 when(item.name){
                     "Manage Account" ->{ manageAccount()}
                     "Change Password" ->{ mFragmentNavigation.pushFragment(ChangePasswordFragment())}
                     "Log Out" ->{ mFragmentNavigation.openDialogFragment(SignoutBottomSheetDialogFragment()
                     )}

                 }
             })


         val list2 = listOf<SettingsClass>(
             SettingsClass("Show Cycle Planner"),
             SettingsClass("Allow push Notification"))

         AppSettingsRecycler.updateRecycler(context!!, list2, R.layout.settings_text_layout, listOf(R.id.Name, R.id.itemSwitch,R.id.contraintLayout),
             { innerViews, position ->
                 val name = innerViews[R.id.Name] as TextView
                 val switch = innerViews[R.id.itemSwitch] as SwitchMaterial
                 val contraintLayout = innerViews[R.id.contraintLayout] as ConstraintLayout

                 name.setText(list2[position].name)

                 contraintLayout.setBackgroundResource(0);


                 switch.setOnCheckedChangeListener { compoundButton, b ->
                     when(position){
                         0->{Toast.makeText(context,"i got to 0", Toast.LENGTH_SHORT).show()}
                         1->{Toast.makeText(context,"i got to 1", Toast.LENGTH_SHORT).show()}
                     }
                 }
             },
             {})


         val list3 = listOf<SettingsClass>(
             SettingsClass("Talk To Support"),
             SettingsClass("Contact Us"))

         AboutUsRecycler.updateRecycler(context!!, list3, R.layout.settings_text_layout, listOf(R.id.Name, R.id.itemSwitch,R.id.contraintLayout),
             { innerViews, position ->
                 val name = innerViews[R.id.Name] as TextView
                 val switch = innerViews[R.id.itemSwitch] as SwitchMaterial
                 val contraintLayout = innerViews[R.id.contraintLayout] as ConstraintLayout
                 switch.gone()


                 name.setText(list3[position].name)
             },
             { position ->
                 val item = list3[position]
                 when(item.name){
                     "Talk To Support" ->{gotoSupportChart()}
                     "Contact Us" ->{ mFragmentNavigation.openBottomSheet(ContactusDialogFragment())}
                 }
             })
     }


     fun manageAccount(){
         if(paperPrefs.getStringPref(PaperPrefs.ENROLSTATUS).equals("N")){
             mFragmentNavigation.pushFragment(EnrolUserFragment())
         } else {
             mFragmentNavigation.pushFragment(ManageAccountFragment())

         }

     }
    fun gotoSupportChart(){
        val config = FreshchatConfig("3d4f39c6-3189-4605-9d41-db43041b4296", "c31a930b-cb40-4b1e-bf29-4584c4e5b95a")
        config.setDomain("msdk.freshchat.com")
        config.setCameraCaptureEnabled(false);
        config.setGallerySelectionEnabled(false);
        config.setResponseExpectationEnabled(false);

        val freshchatUser = Freshchat.getInstance(context!!).user
        freshchatUser.firstName = paperPrefs.getStringPref(PaperPrefs.FIRSTNAME)
        freshchatUser.lastName =  paperPrefs.getStringPref(PaperPrefs.LASTNAME)
        freshchatUser.email = paperPrefs.getStringPref(PaperPrefs.EMAIL)
        freshchatUser.setPhone("+234",  paperPrefs.getStringPref(PaperPrefs.PHONE))

        Freshchat.getInstance(context!!).setUser(freshchatUser).init(config)

        Freshchat.showConversations(context!!);

    }
}

