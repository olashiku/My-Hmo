package com.example.neptune.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doAfterTextChanged
//import com.example.neptune.R
//import com.example.neptune.model.response.Fullbanklist
//import com.example.neptune.viewmodel.FundsTransferViewModel
import com.example.neptune.viewmodel.observeChange
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.qucoon.royalexchange.ui.base.BaseBottomSheetFragment
import com.qucoon.royalexchange.utils.argument
import com.qucoon.royalexchange.utils.updateRecycler
import com.qucoon.royalexchange.utils.withArguments
//import kotlinx.android.synthetic.main.bottom_sheet_layout.*
//import kotlinx.android.synthetic.main.fragment_target_savings1.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.io.Serializable


class BottomSheetFragment : BottomSheetDialogFragment() {
//    private val fundsTransferViewModel: FundsTransferViewModel by sharedViewModel()
//    private lateinit var bankList: List<Fullbanklist>
//    private val possibleBanks:List<Fullbanklist> by argument(POSSIBLE_BANKS)
//    private val showAll:Boolean by argument(SHOWALL)
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.bottom_sheet_layout, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setUpObserver()
//        updateUI()
//
//    }
//
//    private fun setUpSearch() {
//        etSearchBank.doAfterTextChanged {query ->
//            val possibleItems = possibleBanks.filter { it.bankname.contains(query!!,true) ?: false }
//            setUpRecycler(if (query.isNullOrEmpty()) possibleBanks else possibleItems)
//        }
//    }
//
//    private fun updateUI() {
//
//           // setUpRecycler(bankList)
//    }
//
//    private fun setUpAllSearch() {
//        etSearchBank.doAfterTextChanged {query ->
//            val possibleItems = bankList.filter { it.bankname.contains(query!!,true) ?: false }
//            setUpRecycler(if (query.isNullOrEmpty()) bankList else possibleItems)
//        }
//    }
//
//    private fun setUpObserver(){
//        fundsTransferViewModel.getBanksFromDB().observeChange(viewLifecycleOwner){
//            bankList = it
//            if (showAll){
//                setUpAllSearch()
//                setUpRecycler(bankList)
//            }
//            else{
//                setUpSearch()
//                setUpRecycler(possibleBanks)
//            }
//        }
//        }
//
//
//    private fun setUpRecycler(bankList: List<Fullbanklist>) {
//
//        recyclerListOfBanks.updateRecycler(context!!, bankList, R.layout.bottom_sheet_recycler_container,
//            listOf(R.id.bankNames, R.id.bankImages),
//            { innerViews, position ->
//                val name = innerViews[R.id.bankNames] as TextView
//                val logo = innerViews[R.id.bankImages] as ImageView
//                name.text = bankList[position].bankname
//
//                logo.loadImage("https://s3.amazonaws.com/rubiesstore/"+bankList[position].bankcode+".png", R.drawable.bank_building, this)
//            },
//            { position ->
//                val bank = bankList[position]
//                fundsTransferViewModel.onBankSelectedListener.value = bank
//                dialog?.dismiss()
//            })
//
//    }
//
//    companion object{
//        val POSSIBLE_BANKS = "possiblebanks"
//        val SHOWALL = "all"
//
//        fun newInstance(possibleBanks:List<Fullbanklist>):BottomSheetFragment{
//            return BottomSheetFragment().withArguments( POSSIBLE_BANKS to possibleBanks as Serializable,
//                SHOWALL to false)
//        }
//        fun newInstance():BottomSheetFragment{
//            return BottomSheetFragment().withArguments(
//                SHOWALL to true)
//        }
//    }

}