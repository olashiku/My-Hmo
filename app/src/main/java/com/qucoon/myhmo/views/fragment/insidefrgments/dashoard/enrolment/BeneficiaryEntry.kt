package com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.neptune.utils.*
import com.example.neptune.utils.Utils.checkIsEmpty
import com.example.neptune.utils.Utils.checkValidName
import com.example.neptune.utils.Utils.checkvalidemail
import com.example.neptune.utils.Utils.checkvalidphone
import com.qucoon.myhmo.R
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_beneficiary_entry.*
import kotlinx.android.synthetic.main.fragment_beneficiary_entry.etEmail
import kotlinx.android.synthetic.main.fragment_category_display.*
import java.io.Serializable


class BeneficiaryEntry : BaseFragment() {

    val packagee:String  by argument("package")
    val duration:String  by argument("duration")
    val subscriber:String  by argument("subscriber")
    val amount:String  by argument("amount")
    val type:String by argument("type")
    val subtype:String by argument("subtype")
    val subscriber_info:List<BeneficiaryDetails> by argument("subscriber_info")


    var iscomplete = false

    var beneficiarylist: MutableList<BeneficiaryDetails> = mutableListOf()

      var subscriberInHouse:Int  = 0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beneficiary_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnCLick()
        initView()
        initRecycler()
    }

     fun initView(){
         beneficiaryListRecycler.setNestedScrollingEnabled(false);

         beneficiarylist.add(BeneficiaryDetails(paperPrefs.getStringPref(PaperPrefs.FIRSTNAME),paperPrefs.getStringPref(PaperPrefs.LASTNAME),
             paperPrefs.getStringPref(PaperPrefs.EMAIL), paperPrefs.getStringPref(PaperPrefs.PHONE),"Y"))
         subscriberInHouse += subscriber.toInt() -1
     }

     fun initOnCLick(){
         backButtonBeneficiary.setOnClickListener {
             mFragmentNavigation.popFragment()
         }

         benefSubmitButton.setOnClickListener {

             if(!iscomplete){

                 if(checkIsEmpty(etPhone,context,view) && checkIsEmpty(etEmail,context,view)
                     && checkIsEmpty(etFirstBeneficiaryName,context,view) && checkIsEmpty(etSurnameBeneficiaryName,context,view)
                     && checkValidName(etFirstBeneficiaryName,context,view)
                     && checkValidName(etSurnameBeneficiaryName,context,view) && checkvalidphone(etPhone,context,view)
                     && checkvalidemail(etEmail,context,view)){



                     if(subscriberInHouse!=1){

                         subscriberInHouse = subscriberInHouse -1
                         beneficiarylist.add(BeneficiaryDetails(Utils.getTetxt(etFirstBeneficiaryName),Utils.getTetxt(etSurnameBeneficiaryName),Utils.getTetxt(etEmail),
                             Utils.getTetxt(etPhone),"N"))
                         benetitle.show()
                         etPhone.clear()
                         etEmail.clear()
                         etFirstBeneficiaryName.clear()
                         etSurnameBeneficiaryName.clear()
                         initRecycler()
                         Utils.hideKeyboardFrom(context!!,view!!)
                         benefSubmitButton.setText("${subscriberInHouse} beneficiary(s) left")

                     } else {

                         iscomplete = true
                         beneficiarylist.add(BeneficiaryDetails(Utils.getTetxt(etFirstBeneficiaryName),Utils.getTetxt(etSurnameBeneficiaryName),Utils.getTetxt(etEmail),
                             Utils.getTetxt(etPhone),"N"))
                         benefSubmitButton.setText("Click to continue")
                         benetitle.show()
                         etPhone.clear()
                         etEmail.clear()
                         etFirstBeneficiaryName.clear()
                         etSurnameBeneficiaryName.clear()
                         initRecycler()
                         Utils.hideKeyboardFrom(context!!,view!!)
                     }
                 } else {
                     Toast.makeText(context,"Please input values in the required field.",Toast.LENGTH_SHORT).show()
                 }
             } else {
                 mFragmentNavigation.pushFragment(ConfirmationFragment().withArguments(
                     "package" to type,
                     "duration" to duration,
                     "subscriber" to subscriber,
                     "amount" to  amount,
                     "type" to type,
                     "subtype" to subtype,
                     "subscriber_info" to beneficiarylist.toList() as Serializable ))
             }
         }
     }

     fun initRecycler(){

          if(beneficiarylist.size>0){

              benetitle.show()
              beneficiaryListRecycler.show()
              beneficiaryListRecycler.updateRecycler(context!!, beneficiarylist, R.layout.beneficiary_layout, listOf(R.id.nameTV,R.id.emailTV,R.id.removeButton,R.id.phoneTV),
                  { innerViews, position ->
                      val name = innerViews[R.id.nameTV] as TextView
                      val email = innerViews[R.id.emailTV] as TextView
                      val remove = innerViews[R.id.removeButton] as Button
                      val phone = innerViews[R.id.phoneTV] as TextView

                      name.setText(beneficiarylist[position].lastname +" " +beneficiarylist[position].firstname)
                      email.setText("Email : ${beneficiarylist[position].email}")
                      phone.setText("Phone : ${beneficiarylist[position].phone}")

                      remove.setOnClickListener{
                          removeBeneficiary(beneficiarylist[position], beneficiarylist.size)
                      }
                  },
                  { position -> val item = beneficiarylist[position] })
          }


     }

    private fun removeBeneficiary(index: BeneficiaryDetails, size: Int) {

        if(!paperPrefs.getStringPref(PaperPrefs.EMAIL).equals(index.email)){
            if(size == 1){
                beneficiarylist.remove(index)
                beneficiaryListRecycler.gone()
                benetitle.gone()
                subscriberInHouse  = beneficiarylist.size
                subscriberInHouse += subscriber.toInt() -1
                benefSubmitButton.setText("Submit")
                iscomplete = false
            }else {
                iscomplete = false
                beneficiarylist.remove(index)
                subscriberInHouse  = subscriberInHouse +1
                benefSubmitButton.setText("${subscriberInHouse} beneficiary(s) left")
                initRecycler()
            }
        } else {
            Toast.makeText(context,"You cannot delete yourself as a sponsor", Toast.LENGTH_SHORT).show()
        }



    }


}


data class BeneficiaryDetails(
    val firstname:String,
    val lastname:String,
    val email:String,
    val phone:String,
    val isowner:String
):Serializable