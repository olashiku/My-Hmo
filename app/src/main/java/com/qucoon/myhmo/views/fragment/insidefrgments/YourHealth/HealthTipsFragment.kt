package com.qucoon.myhmo.views.fragment.insidefrgments.YourHealth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.neptune.utils.updateRecycler
import com.example.neptune.utils.withArguments
import com.qucoon.myhmo.R
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.myhmo.views.fragment.insidefrgments.YourHealth.HealthTips.HealthTipsDisplayFragment
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_health_tips.*
import kotlinx.android.synthetic.main.fragment_hospital2.*
import kotlinx.android.synthetic.main.fragment_profile2.*
import java.io.Serializable


class HealthTipsFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health_tips, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
        insertValues()
    }

     fun initView(){
         (activity as MainActivity).hideTablayout()

     }

     fun initOnClick(){
         backButtonHealthTips.setOnClickListener { mFragmentNavigation.popFragment() }
     }


     fun insertValues(){
          val list = listOf<HealthData>(
              HealthData("https://myhmoimagebucket.s3.us-east-2.amazonaws.com/1_HEoLBLidT2u4mhJ0oiDgig.png","Feel Good About Yourself Today","Be sure the people around you make you feel good about you -- no matter what your size or health condition. In addition, if close friends encourage you to smoke, overeat, or drink too much, find some new friends who have good health habits and also want a healthier you.\n" +
                      "" + "Elaine Magee, MPH, Rd, author of more than 20 books, says don't get hung up on pounds or what size dress you are wearing." +"" + "\"Instead, focus on being healthy from the inside out,\" Magee says. \"Eat well, and exercise regularly. And remember that you can be sexy and look and feel fabulous and not be thin.\""),
              HealthData("https://myhmoimagebucket.s3.us-east-2.amazonaws.com/1_HEoLBLidT2u4mhJ0oiDgig.png","Feel Good About Yourself Today","Be sure the people around you make you feel good about you -- no matter what your size or health condition. In addition, if close friends encourage you to smoke, overeat, or drink too much, find some new friends who have good health habits and also want a healthier you.\n" +
                      "" + "Elaine Magee, MPH, Rd, author of more than 20 books, says don't get hung up on pounds or what size dress you are wearing." +"" + "\"Instead, focus on being healthy from the inside out,\" Magee says. \"Eat well, and exercise regularly. And remember that you can be sexy and look and feel fabulous and not be thin.\""),
              HealthData("https://myhmoimagebucket.s3.us-east-2.amazonaws.com/1_HEoLBLidT2u4mhJ0oiDgig.png","Feel Good About Yourself Today","Be sure the people around you make you feel good about you -- no matter what your size or health condition. In addition, if close friends encourage you to smoke, overeat, or drink too much, find some new friends who have good health habits and also want a healthier you.\n" +
                      "" + "Elaine Magee, MPH, Rd, author of more than 20 books, says don't get hung up on pounds or what size dress you are wearing." +"" + "\"Instead, focus on being healthy from the inside out,\" Magee says. \"Eat well, and exercise regularly. And remember that you can be sexy and look and feel fabulous and not be thin.\""),
              HealthData("https://myhmoimagebucket.s3.us-east-2.amazonaws.com/1_HEoLBLidT2u4mhJ0oiDgig.png","Feel Good About Yourself Today","Be sure the people around you make you feel good about you -- no matter what your size or health condition. In addition, if close friends encourage you to smoke, overeat, or drink too much, find some new friends who have good health habits and also want a healthier you.\n" +
                      "" + "Elaine Magee, MPH, Rd, author of more than 20 books, says don't get hung up on pounds or what size dress you are wearing." +"" + "\"Instead, focus on being healthy from the inside out,\" Magee says. \"Eat well, and exercise regularly. And remember that you can be sexy and look and feel fabulous and not be thin.\""),
              HealthData("https://myhmoimagebucket.s3.us-east-2.amazonaws.com/1_HEoLBLidT2u4mhJ0oiDgig.png","Feel Good About Yourself Today","Be sure the people around you make you feel good about you -- no matter what your size or health condition. In addition, if close friends encourage you to smoke, overeat, or drink too much, find some new friends who have good health habits and also want a healthier you.\n" +
                      "" + "Elaine Magee, MPH, Rd, author of more than 20 books, says don't get hung up on pounds or what size dress you are wearing." +"" + "\"Instead, focus on being healthy from the inside out,\" Magee says. \"Eat well, and exercise regularly. And remember that you can be sexy and look and feel fabulous and not be thin.\""),
              HealthData("https://myhmoimagebucket.s3.us-east-2.amazonaws.com/1_HEoLBLidT2u4mhJ0oiDgig.png","Feel Good About Yourself Today","Be sure the people around you make you feel good about you -- no matter what your size or health condition. In addition, if close friends encourage you to smoke, overeat, or drink too much, find some new friends who have good health habits and also want a healthier you.\n" +
                      "" + "Elaine Magee, MPH, Rd, author of more than 20 books, says don't get hung up on pounds or what size dress you are wearing." +"" + "\"Instead, focus on being healthy from the inside out,\" Magee says. \"Eat well, and exercise regularly. And remember that you can be sexy and look and feel fabulous and not be thin.\""),
         HealthData("https://myhmoimagebucket.s3.us-east-2.amazonaws.com/1_HEoLBLidT2u4mhJ0oiDgig.png","Feel Good About Yourself Today","Be sure the people around you make you feel good about you -- no matter what your size or health condition. In addition, if close friends encourage you to smoke, overeat, or drink too much, find some new friends who have good health habits and also want a healthier you.\n" +
                 "" + "Elaine Magee, MPH, Rd, author of more than 20 books, says don't get hung up on pounds or what size dress you are wearing." +"" + "\"Instead, focus on being healthy from the inside out,\" Magee says. \"Eat well, and exercise regularly. And remember that you can be sexy and look and feel fabulous and not be thin.\""))
          updateRecycler(list)
     }


    fun updateRecycler(list: List<HealthData>) {

        tipsRecycler.updateRecycler(context!!, list, R.layout.health_tips_layout, listOf(R.id.imageview, R.id.tipTitle, R.id.tipBody),
            { innerViews, position ->
                val image = innerViews[R.id.imageview] as ImageView
                val tipTitle = innerViews[R.id.tipTitle] as TextView
                val tipBody = innerViews[R.id.tipBody] as TextView


                tipTitle.text = list[position].title
                tipBody.text = list[position].description

                Glide.with(this).load( list[position].image).into(image);

            },
            { position ->
                mFragmentNavigation.pushFragment(HealthTipsDisplayFragment().withArguments(
                    "HealthData" to list[position]
                ))
            })

    }


}

data class HealthData(
    var image:String,
    var title:String,
    var description:String
):Serializable