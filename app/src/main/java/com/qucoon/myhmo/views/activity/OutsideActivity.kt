package com.qucoon.myhmo.views.activity

import android.annotation.SuppressLint
import android.content.IntentSender
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.style.TypefaceSpan
import android.util.Log
import com.baurine.permissionutil.PermissionUtil
import com.example.neptune.utils.Utils
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import com.qucoon.keystonemobile.utils.CheckPermissionUtil
import com.qucoon.myhmo.R
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.savePref
import com.qucoon.myhmo.database.saveToPref
import com.qucoon.myhmo.views.fragment.outsidefragment.LoginFragment
import com.qucoon.royalexchange.ui.base.BaseActivity
import timber.log.Timber
import java.text.DateFormat
import java.util.*



class OutsideActivity : BaseActivity() {

    lateinit var  paperPrefs:PaperPrefs


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outside)
        initView()
    }

    fun initView(){
        paperPrefs = PaperPrefs(application)
        supportActionBar!!.hide()
        Utils.setStatusBarColor(this,"#107FB2")
        val fragment = listOf(LoginFragment())
        initFragNavController(this,fragment,"OUTSIDEACTIVITY",supportFragmentManager,R.id.content)
    }

     fun showToolsbarAndSetTitle(title:String){
         val typefaceSpan = TypefaceSpan("poppinsregular.ttf")
         val str = SpannableString(title)
         str.setSpan(typefaceSpan, 0, str.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
         supportActionBar!!.title = Html.fromHtml("<font color='#ffffff'>$title</font>")
         supportActionBar!!.show()
     }

     fun hideToolsBar(){
         supportActionBar!!.hide()
     }








}
