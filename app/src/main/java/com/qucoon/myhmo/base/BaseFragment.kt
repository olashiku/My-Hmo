package com.qucoon.royalexchange.ui.base

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.neptune.viewmodel.BaseViewModel
import com.example.neptune.viewmodel.observeChange
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.views.UitilityView.ViewDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

import kotlin.coroutines.CoroutineContext


open class BaseFragment : Fragment(), CoroutineScope {

    //val backgroundRepository:BackgroundWorkRepository by inject()
    lateinit var mFragmentNavigation: FragmentNavigation
    lateinit  var alertDialog: AlertDialog
    lateinit var paperPrefs: PaperPrefs
    val backgroudJobs =  Job()
    var viewDialog: ViewDialog? = null

    override val coroutineContext: CoroutineContext
        get() = backgroudJobs + Dispatchers.Main



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentNavigation) {
            paperPrefs = PaperPrefs(context)
            mFragmentNavigation = context
            viewDialog = ViewDialog(activity)

        }
    }
    fun showloadingDialog(show:Boolean){
        if (show) viewDialog!!.showDialog() else viewDialog!!.hideDialog()
    }

    interface FragmentNavigation {

        fun pushFragment(fragment: Fragment)
        fun popFragment()
        fun popFragments(n:Int)
        fun openDialogFragment(fragment:DialogFragment)
        fun openBottomSheet(bottomSheetDialogFragment: BottomSheetDialogFragment)
        fun clearStack()
        fun clearDialogFragmentStack()
        fun switchFragment(index: Int)
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelAllJobs()
    }
    fun cancelAllJobs(){
        try{
            backgroudJobs.cancel()
        }catch (ex:Exception){

        }
    }

    fun setUpObservers(viewModel: BaseViewModel){
        setUpErrorHandler(viewModel)
        setUpLoading(viewModel)
    }

    private fun setUpErrorHandler(viewModel: BaseViewModel){
        viewModel.showError.observeChange(this){showError(it)}
    }


    fun setUpErrorHandler(viewModel: BaseViewModel, action:()->Unit){
        viewModel.showError.observeChange(this){
            action()
            showError(it)
        }
    }


    fun setUpLoading(viewModel: BaseViewModel){
        viewModel.showLoading.observeChange(this){status -> showLoading(status)}
    }

    fun showLoading(status:Boolean){
        if (status) showloadingDialog(true) else showloadingDialog(false)
    }

    fun showError(errorMessage:String){
        Toast.makeText(context,errorMessage,Toast.LENGTH_SHORT).show()
    }

}
