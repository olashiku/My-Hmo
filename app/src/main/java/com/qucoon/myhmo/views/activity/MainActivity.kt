package com.qucoon.myhmo.views.activity

import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.style.TypefaceSpan
import com.example.neptune.utils.Constants
import com.example.neptune.utils.gone
import com.example.neptune.utils.makeStatusBarTransparent
import com.example.neptune.utils.show
import com.freshchat.consumer.sdk.Freshchat
import com.freshchat.consumer.sdk.FreshchatConfig
import com.qucoon.myhmo.R
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.myhmo.views.fragment.insidefrgments.ApointmentFragment
import com.qucoon.myhmo.views.fragment.insidefrgments.DashboardFragment
import com.qucoon.myhmo.views.fragment.insidefrgments.SettingsFragment
import com.qucoon.royalexchange.ui.base.BaseActivity
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var  paperPrefs:PaperPrefs


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initBottomNavigation()
        showToolsbarAndSetTitle("Dashboard")
        initFreshChat()
    }


    fun initFreshChat(){
        val config = FreshchatConfig(Constants.FRESHCHARAPPID ,Constants.FRESHCHATKEY )
        config.setDomain(Constants.FRESHCHATDOMAIN)
        config.setCameraCaptureEnabled(false);
        config.setGallerySelectionEnabled(false);
        config.setResponseExpectationEnabled(true);


        val freshchatUser = Freshchat.getInstance(this).user
        freshchatUser.firstName = paperPrefs.getStringPref(PaperPrefs.FIRSTNAME)
        freshchatUser.lastName =  paperPrefs.getStringPref(PaperPrefs.LASTNAME)
        freshchatUser.email = paperPrefs.getStringPref(PaperPrefs.EMAIL)
        freshchatUser.setPhone("+234",  paperPrefs.getStringPref(PaperPrefs.PHONE))

        Freshchat.getInstance(this).setUser(freshchatUser).init(config)
    }

    fun showToolsbarAndSetTitle(title:String){

        println("soigothere" + title)
        val typefaceSpan = TypefaceSpan("poppinsregular.ttf")
        val str = SpannableString(title)
        str.setSpan(typefaceSpan, 0, str.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        supportActionBar!!.title = Html.fromHtml("<font color='#ffffff'>$title</font>")
      //
    }

     fun setActoonBarTitle(title:String){
         supportActionBar!!.title = Html.fromHtml("<font color='#ffffff'>$title</font>")
     }

     fun hideTablayout(){
         tabLayout.gone()
     }

     fun showTablayout(){
         tabLayout.show()
     }


    fun hideToolsBar(){
        supportActionBar!!.hide()
    }

    fun initView(){
        paperPrefs = PaperPrefs(application)
        supportActionBar!!.show()

        val fragment = listOf(DashboardFragment(), ApointmentFragment(), SettingsFragment())
        initFragNavController(this,
            fragment as List<BaseFragment>,"MAINACTIVITY",supportFragmentManager,R.id.content)

    }

    fun initBottomNavigation(){
        tabLayout.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_menu -> {
                    setActoonBarTitle("Dashboard")
                    switchFragment(HOME)

                }

                R.id.search_menu -> {
                    setActoonBarTitle("Your Health")
                    switchFragment(SEARCHMENU)
                }
                R.id.profile_menu -> {
                    setActoonBarTitle("Settings")
                    switchFragment(PROFILEMENU)
                }
            }
            true
        }
    }


    companion object{
        val  HOME=0
        val  SEARCHMENU=1
        val  PROFILEMENU=2



    }
   
}
