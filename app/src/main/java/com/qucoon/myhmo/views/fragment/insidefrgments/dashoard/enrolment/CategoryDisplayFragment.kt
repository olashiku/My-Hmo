package com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.neptune.utils.Utils
import com.example.neptune.utils.argument
import com.example.neptune.utils.updateRecycler
import com.example.neptune.utils.withArguments
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.qucoon.myhmo.R
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.nibbs.utils.Validator
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_category_display.*
import kotlinx.android.synthetic.main.fragment_category_display.submitButton
import kotlinx.android.synthetic.main.fragment_category_display.tvTitleDetails
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_profile2.*
import java.io.Serializable
import java.text.FieldPosition

class CategoryDisplayFragment : BaseFragment() {

    val type:String by argument("type")
    val subtype:String by argument("subtype")

    var beneficiarylist: MutableList<BeneficiaryDetails> = mutableListOf()


    lateinit var  duration:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_display, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        watchTextField()
        initrecycler()
        initOnClick()
    }

     fun watchTextField(){
         etNumberOfPeople.doAfterTextChanged { Validator.isValidNumber(etNumberOfPeople)}

     }


     fun initrecycler(){

          when(type){
              "gold" -> {
                  performGoldStuffs()
              }
              "platinum" -> {
                  performPlatinumStuff()
              }
          }
     }

     fun performPlatinumStuff(){

         tvTitleDetails.setText("Platinum ${subtype.capitalize()}")
         etAmount.setText("₦6,000.00")
         tvTypeTitle.setText("Monthly")
         tvTypeSubtitle.setText("Billed Monthly")

         val list = listOf<CategoryInformation>(
             CategoryInformation("Combined medical cover of ₦1.8 Million naira a year"),
             CategoryInformation("Access to health specialists (based on referral) **"),
             CategoryInformation("₦300,000 for 14 different surgeries available yearly."),
             CategoryInformation("Free wellness and health checks at selected Reliance centres"),
             CategoryInformation("Access to eye and dental care *"),
             CategoryInformation("Access to gym *")
         )

         recyclerView.updateRecycler(context!!, list, R.layout.category_layout, listOf(R.id.nameTV),
             { innerViews, position ->
                 val name = innerViews[R.id.nameTV] as TextView
                 name.setText(list[position].name)

             },
             { position -> val item = list[position] })

     }


     fun performGoldStuffs(){

         tvTitleDetails.setText("Gold $subtype")
         etAmount.setText("₦3,500.00")
         tvTypeTitle.setText("Monthly")
         tvTypeSubtitle.setText("Billed Monthly")

         val list = listOf<CategoryInformation>(
             CategoryInformation("Combined medical cover of ₦1.2 Million naira a year"),
             CategoryInformation("Access to health specialists (based on referral) **"),
             CategoryInformation("₦150,000 for 9 different surgeries available yearly."),
             CategoryInformation("Free wellness and health checks at selected Reliance centres"),
             CategoryInformation("Access to eye and dental care *"),
             CategoryInformation("Access to gym *")
         )

         recyclerView.updateRecycler(context!!, list, R.layout.category_layout, listOf(R.id.nameTV),
             { innerViews, position ->
                 val name = innerViews[R.id.nameTV] as TextView
                 name.setText(list[position].name)

             },
             { position -> val item = list[position] })

     }

     fun initOnClick(){

         backButtonCatType.setOnClickListener {
             mFragmentNavigation.popFragment()
         }

         submitButton.setOnClickListener {

            if(Validator.isValidNumber(etNumberOfPeople)){

                if(Utils.getTetxt(etNumberOfPeople).toInt()== 1){
                    beneficiarylist.add(BeneficiaryDetails(paperPrefs.getStringPref(PaperPrefs.FIRSTNAME),paperPrefs.getStringPref(
                        PaperPrefs.LASTNAME),paperPrefs.getStringPref(PaperPrefs.EMAIL), paperPrefs.getStringPref(PaperPrefs.PHONE),"Y"))


                    mFragmentNavigation.pushFragment(ConfirmationFragment().withArguments(
                        "package" to type,
                        "duration" to duration,
                        "subscriber" to Utils.getTetxt(etNumberOfPeople),
                        "amount" to  etAmount.text.toString(),
                        "type" to type,
                        "subtype" to subtype,
                        "subscriber_info" to beneficiarylist.toList() as Serializable ))
                }else {

                    mFragmentNavigation.pushFragment(BeneficiaryEntry().withArguments(
                        "package" to type,
                        "duration" to duration,
                        "subscriber" to Utils.getTetxt(etNumberOfPeople),
                        "amount" to  etAmount.text.toString(),
                        "type" to type,
                        "subtype" to subtype,
                        "subscriber_info" to beneficiarylist.toList() as Serializable
                    ))

                }

            } else {
                Toast.makeText(context,"Please enter a number to proced", Toast.LENGTH_SHORT).show()
            }


         }


     }

     fun initView(){

         recyclerView.setNestedScrollingEnabled(false);


         when(subtype){
            "individual"->{
                etNumberOfPeople.setText("1")
                etNumberOfPeople.isEnabled =false
            }

         }




         duration ="Monthly"
         tab.addTab(tab.newTab().setText("Monthly"));
         tab.addTab(tab.newTab().setText("Quarterly"));
         tab.addTab(tab.newTab().setText("Annually"));

         tab.addOnTabSelectedListener(object : OnTabSelectedListener {
             override fun onTabSelected(tab: TabLayout.Tab) {

               changetabBasedOnType(type,tab.position)

             }

             override fun onTabUnselected(tab: TabLayout.Tab) {}
             override fun onTabReselected(tab: TabLayout.Tab) {}
         })

     }


     fun changetabBasedOnType(type:String,position: Int){

         when(subtype){
             "individual"->{
                 etNumberOfPeople.setText("1")
                 etNumberOfPeople.isEnabled =false


                 when(type){
                     "gold"->{

                         when(position){
                             0->{
                               //  etNumberOfPeople.text!!.clear()
                                 duration= "Monthly"
                                 tvTypeTitle.setText("Monthly")
                                 tvTypeSubtitle.setText("Billed Monthly")
                                 etAmount.setText("₦3,500.00")
                             }
                             1->{
                             //    etNumberOfPeople.text!!.clear()
                                 duration= "Quaterly"
                                 tvTypeTitle.setText("Quaterly")
                                 tvTypeSubtitle.setText("Billed Quaterly")
                                 etAmount.setText("₦10,350.00")
                             }
                             2->{
                             //    etNumberOfPeople.text!!.clear()
                                 duration= "Annually"
                                 tvTypeTitle.setText("Annually")
                                 tvTypeSubtitle.setText("Billed Annually")
                                 etAmount.setText("₦38,650.00")

                             }
                         }


                     }
                     "platinum"->{

                         when(position){
                             0->{
                                 duration= "Monthly"
                                 tvTypeTitle.setText("Monthly")
                                 tvTypeSubtitle.setText("Billed Monthly")
                                 etAmount.setText("₦6,000.00")
                             }
                             1->{
                                 duration= "Quaterly"
                                 tvTypeTitle.setText("Quaterly")
                                 tvTypeSubtitle.setText("Billed Quaterly")
                                 etAmount.setText("₦17,750.00")
                             }
                             2->{
                                 duration= "Annually"
                                 tvTypeTitle.setText("Annually")
                                 tvTypeSubtitle.setText("Billed Annually")
                                 etAmount.setText("₦66,250.00")
                             }
                         }

                     }
                 }



             } else ->{

             when(type){
                 "gold"->{

                     when(position){
                         0->{
                             etNumberOfPeople.text!!.clear()
                             duration= "Monthly"
                             tvTypeTitle.setText("Monthly")
                             tvTypeSubtitle.setText("Billed Monthly")
                             etAmount.setText("₦3,500.00")
                         }
                         1->{
                             etNumberOfPeople.text!!.clear()
                             duration= "Quaterly"
                             tvTypeTitle.setText("Quaterly")
                             tvTypeSubtitle.setText("Billed Quaterly")
                             etAmount.setText("₦10,350.00")
                         }
                         2->{
                             etNumberOfPeople.text!!.clear()
                             duration= "Annually"
                             tvTypeTitle.setText("Annually")
                             tvTypeSubtitle.setText("Billed Annually")
                             etAmount.setText("₦38,650.00")

                         }
                     }


                 }
                 "platinum"->{

                     when(position){
                         0->{
                             duration= "Monthly"
                             tvTypeTitle.setText("Monthly")
                             tvTypeSubtitle.setText("Billed Monthly")
                             etAmount.setText("₦6,000.00")
                         }
                         1->{
                             duration= "Quaterly"
                             tvTypeTitle.setText("Quaterly")
                             tvTypeSubtitle.setText("Billed Quaterly")
                             etAmount.setText("₦17,750.00")
                         }
                         2->{
                             duration= "Annually"
                             tvTypeTitle.setText("Annually")
                             tvTypeSubtitle.setText("Billed Annually")
                             etAmount.setText("₦66,250.00")
                         }
                     }

                 }
             }


         }

         }




     }
}

data class CategoryInformation(
    val name: String
)