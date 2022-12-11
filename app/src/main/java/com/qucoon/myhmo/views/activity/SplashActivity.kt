package com.qucoon.myhmo.views.activity

import android.os.Bundle
import com.example.neptune.utils.makeStatusBarTransparent
import com.qucoon.myhmo.R
import com.qucoon.myhmo.views.fragment.outsidefragment.SplashFragment
import com.qucoon.royalexchange.ui.base.BaseActivity

class SplashActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        initView()
    }

    fun initView(){
        supportActionBar!!.hide()
            val fragment = listOf(SplashFragment())
            initFragNavController(this,fragment,"SPLASHACTIVITY",supportFragmentManager,R.id.container)
        this.makeStatusBarTransparent()


    }


}





