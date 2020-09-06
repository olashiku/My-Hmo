package com.qucoon.myhmo.views.activity

import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.style.TypefaceSpan
import com.example.neptune.utils.makeStatusBarTransparent
import com.qucoon.myhmo.R
import com.qucoon.myhmo.views.fragment.insidefrgments.ApointmentFragment
import com.qucoon.myhmo.views.fragment.insidefrgments.DashboardFragment
import com.qucoon.myhmo.views.fragment.insidefrgments.ProfileFragment
import com.qucoon.royalexchange.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initBottomNavigation()
        showToolsbarAndSetTitle("Dashboard")
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



    fun hideToolsBar(){
        supportActionBar!!.hide()
    }

    fun initView(){
        supportActionBar!!.show()
        val fragment = listOf(DashboardFragment(), ApointmentFragment(), ProfileFragment())
        initFragNavController(this,fragment,"MAINACTIVITY",supportFragmentManager,R.id.content)
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
