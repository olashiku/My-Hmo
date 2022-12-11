package com.qucoon.myhmo.views.fragment.outsidefragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.baurine.permissionutil.PermissionUtil
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.neptune.utils.hide
import com.example.neptune.utils.show
import com.qucoon.keystonemobile.utils.CheckPermissionUtil
import com.qucoon.myhmo.dataclasses.OnboardDataClass

import com.qucoon.myhmo.R
import com.qucoon.myhmo.views.activity.OutsideActivity
import com.qucoon.myhmo.views.UitilityView.OnboardItemFragment
import com.qucoon.royalexchange.ui.base.BaseFragment
import com.qucoon.royalexchange.utils.ViewPagerObject
import com.qucoon.royalexchange.utils.setUpViewPager
import kotlinx.android.synthetic.main.fragment_onboard.*


class OnboardFragment : BaseFragment(), ViewPager.OnPageChangeListener {

    var count =0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initTab()
        initOnClick()
        initView()
    }

    fun initView(){

      var hasvalise=  CheckPermissionUtil.hasLocationPermission(activity!!)

        if(!hasvalise){
            CheckPermissionUtil.checkLocation(activity!!, PermissionUtil.ReqPermissionCallback { b -> if (b) {}})
        } else {
            CheckPermissionUtil.checkLocation(activity!!, PermissionUtil.ReqPermissionCallback { b -> if (b) {}})
        }



    }

    fun initOnClick(){

        nectbutton.setOnClickListener {
            onboardviewpager.setCurrentItem(count+1)
        }

        previousbutton.setOnClickListener {

            onboardviewpager.setCurrentItem(count-1)
        }

        getstartedbutton.setOnClickListener {
            startActivity(Intent(activity, OutsideActivity::class.java))
        }


    }

    fun initTab(){
        for (i in 0 until tabDots.tabCount) {
            val tab = (tabDots.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(10, 0, 10, 0)
            tab.requestLayout()
        }
    }

    fun initViewPager(){

        val list= listOf<OnboardDataClass>(
            OnboardDataClass(
                R.drawable.boardimage1,
                "Get a list of nearby",
                "experienced doctors",
                "in just seconds."
            ),
            OnboardDataClass(
                R.drawable.boardimage2,
                "Book your",
                "appointment",
                "and save your time."
            ),
            OnboardDataClass(
                R.drawable.boardimage3,
                "Get your whole",
                "body checkup",
                "on given time."
            )
        )


        val items = list.map { ViewPagerObject(OnboardItemFragment.newInstance(it),"")  }
        onboardviewpager.setUpViewPager(items,childFragmentManager)
        onboardviewpager.addOnPageChangeListener(this)
        tabDots.setupWithViewPager(onboardviewpager)

    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        count = position
        when(position){
            0->{showNextbutton()}
            1->{showBothButton()}
            2->{showGetstarted()}
        }
    }

    fun showNextbutton(){

        previousbutton.hide(); nectbutton.show(); getstartedbutton.visibility = View.INVISIBLE
        animateButton(nectbutton)
    }

    fun showBothButton(){
        previousbutton.show(); nectbutton.show();getstartedbutton.visibility = View.INVISIBLE
        animateButton2(previousbutton)
        animateButton(nectbutton)

    }


    fun showGetstarted(){
        previousbutton.hide(); nectbutton.hide();getstartedbutton.visibility = View.VISIBLE
        animateButton(getstartedbutton)

    }


    fun animateButton(view:View){
        YoYo.with(Techniques.FlipInY)
            .duration(1000)
            .repeat(0)
            .playOn(view);
    }

    fun animateButton2(view:View){
        YoYo.with(Techniques.FlipInX)
            .duration(1000)
            .repeat(0)
            .playOn(view);
    }
}
