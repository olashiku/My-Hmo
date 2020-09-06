package com.qucoon.myhmo.views.fragment.insidefrgments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.neptune.utils.gone
import com.example.neptune.utils.updateRecycler
import com.google.android.material.switchmaterial.SwitchMaterial

import com.qucoon.myhmo.R
import com.qucoon.myhmo.dataclasses.SettingsClass
import com.qucoon.myhmo.popups.utilitypupups.SignoutBottomSheetDialogFragment
import com.qucoon.myhmo.views.activity.MainActivity
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
class ProfileFragment : BaseFragment() {

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
                     "Manage Account" ->{ mFragmentNavigation.openDialogFragment(SignoutBottomSheetDialogFragment())}
                     "Change Password" ->{ mFragmentNavigation.openDialogFragment(SignoutBottomSheetDialogFragment())}
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
                     "Talk To Support" ->{Toast.makeText(context,"i got to Talk To Support", Toast.LENGTH_SHORT).show()}
                     "Contact Us" ->{ Toast.makeText(context,"i got to Contact Us", Toast.LENGTH_SHORT).show()}
                 }
             })
     }
}

