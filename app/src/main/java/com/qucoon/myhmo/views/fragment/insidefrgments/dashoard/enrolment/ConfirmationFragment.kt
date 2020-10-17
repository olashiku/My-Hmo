package com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.neptune.utils.*
import com.qucoon.myhmo.R
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.myhmo.popups.AutomateRequestDialogFragment
import com.qucoon.myhmo.viewmodel.DashboardViewModel
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_category_display.*
import kotlinx.android.synthetic.main.fragment_confirmation.*
import kotlinx.android.synthetic.main.fragment_confirmation.submitButton
import org.koin.android.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConfirmationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConfirmationFragment : BaseFragment(),AutomateRequestDialogFragment.AutomateDialogCallback {


     val packagee:String  by argument("package")
    val duration:String  by argument("duration")
    val subscriber:String  by argument("subscriber")
    val amount:String  by argument("amount")
    val type:String by argument("type")
    val subtype:String by argument("subtype")

     var schedulapayment ="N"


    lateinit var  calculatedAMount:String

     val dashboardViewModel: DashboardViewModel by viewModel()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClick()
        initView()
        initViewModel()
        initSwitch()
    }

     fun initSwitch(){

         switch1.setOnCheckedChangeListener { buttonView, isChecked ->

             when(isChecked) {
                 true->{ mFragmentNavigation.openDialogFragment(AutomateRequestDialogFragment()) }
             }
         }
     }

     fun initViewModel(){
         setUpObservers(dashboardViewModel)
         dashboardViewModel.initpaymentResponse.observe(viewLifecycleOwner, Observer {

             mFragmentNavigation.pushFragment(PaystackFragment().withArguments(
                 "paystackurl" to it.result.authorization_url,
                 "package" to type,
                 "duration" to duration,
                 "subscriber" to subscriber,
                 "amount" to  amount,
                 "type" to type,
                 "subtype" to subtype,
                 "reference" to it.result.reference,
                 "schedulapayment" to schedulapayment

             ))
         })

     }

     fun initView(){
         tvPackageType.setText("${packagee.capitalize()} ${subtype.capitalize()}")
         tvDuration.setText(duration)
         tvSubscriber.setText(subscriber)
         tvAmount.setText(amount)
         tvAmount2.setText(calculateAmount(subscriber,amount).formatNumber())
         calculatedAMount = calculateAmount(subscriber,amount).formatNumber()
     }

    fun calculateAmount(duration:String, amount:String):String{
        val total = amount.stripAmount().replace(".00","").trim().toInt()* duration.toInt()
        return total.toString()
    }



     fun initOnClick(){
         backButtonCatTypePackage.setOnClickListener {
             mFragmentNavigation.popFragment()
         }

         submitButton.setOnClickListener {
             dashboardViewModel.initPayment(calculatedAMount.stripAmount().replace(".00","00"),
                 paperPrefs.getStringPref(PaperPrefs.EMAIL))


         }
     }

    override fun automateStatus(value: String) {
        when(value){
            "yes"->{
                schedulapayment = "Y"
                Toast.makeText(context,"Your payment will be rescheduled", Toast.LENGTH_SHORT).show()
            }
            "no"->{schedulapayment = "N"
                Toast.makeText(context,"Your payment will not be rescheduled", Toast.LENGTH_SHORT).show()
            }


        }
    }
}