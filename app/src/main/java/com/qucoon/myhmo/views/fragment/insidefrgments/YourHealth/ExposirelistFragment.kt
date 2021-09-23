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
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.myhmo.views.fragment.insidefrgments.YourHealth.HealthTips.HealthTipsDisplayFragment
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_exposirelist.*


class ExposirelistFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exposirelist, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initOnClck()
        initView()
    }

     fun initOnClck(){
         backButtonExposure.setOnClickListener { mFragmentNavigation.popFragment() }
     }


     fun initView(){

         (activity as MainActivity).hideTablayout()

         val list = listOf<ExposureData>(
             ExposureData("Chest X-Rays","COVERED"),
             ExposureData("Plain Abdominal X-Rays","COVERED"),
             ExposureData("Limbs X-Rays","COVERED"),
             ExposureData("Skull X-Rays","COVERED"),
             ExposureData("Lumbosacral X-Rays","COVERED"),
             ExposureData("Serum Bilirubin","COVERED"),
             ExposureData("Serum albumin","COVERED"),
             ExposureData("Chest X-Rays","COVERED"),
             ExposureData("Plain Abdominal X-Rays","COVERED"),
             ExposureData("Limbs X-Rays","COVERED"),
             ExposureData("Skull X-Rays","COVERED"),
             ExposureData("Lumbosacral X-Rays","COVERED"),
             ExposureData("Serum Bilirubin","COVERED"),
             ExposureData("Serum albumin","COVERED"),
             ExposureData("Limbs X-Rays","COVERED"),
             ExposureData("Skull X-Rays","COVERED"),
             ExposureData("Lumbosacral X-Rays","COVERED"),
             ExposureData("Serum Bilirubin","COVERED"),
             ExposureData("Serum albumin","COVERED"),
             ExposureData("Chest X-Rays","COVERED"),
             ExposureData("Plain Abdominal X-Rays","COVERED"),
             ExposureData("Limbs X-Rays","COVERED"),
             ExposureData("Skull X-Rays","COVERED"),
             ExposureData("Lumbosacral X-Rays","COVERED"),
             ExposureData("Limbs X-Rays","COVERED"),
             ExposureData("Skull X-Rays","COVERED"),
             ExposureData("Lumbosacral X-Rays","COVERED"),
             ExposureData("Serum Bilirubin","COVERED"),
             ExposureData("Serum albumin","COVERED"),
             ExposureData("Chest X-Rays","COVERED"),
             ExposureData("Plain Abdominal X-Rays","COVERED"),
             ExposureData("Limbs X-Rays","COVERED"),
             ExposureData("Skull X-Rays","COVERED"),
             ExposureData("Lumbosacral X-Rays","COVERED"),
             ExposureData("Limbs X-Rays","COVERED"),
             ExposureData("Skull X-Rays","COVERED"),
             ExposureData("Lumbosacral X-Rays","COVERED"),
             ExposureData("Serum Bilirubin","COVERED"),
             ExposureData("Serum albumin","COVERED"),
             ExposureData("Chest X-Rays","COVERED"),
             ExposureData("Plain Abdominal X-Rays","COVERED"),
             ExposureData("Limbs X-Rays","COVERED"),
             ExposureData("Skull X-Rays","COVERED"),
             ExposureData("Lumbosacral X-Rays","COVERED"))

          updaterecycler(list)
     }

     fun updaterecycler(list: List<ExposureData>) {
         benefitRecycler.updateRecycler(context!!, list, R.layout.exposure_list_blueprint, listOf(R.id.tvCoverName, R.id.tvCoverStatus),
             { innerViews, position ->
                 val tvCoverName = innerViews[R.id.tvCoverName] as TextView
                 val tvCoverStatus = innerViews[R.id.tvCoverStatus] as TextView


                 tvCoverName.text = list[position].name
                 tvCoverStatus.text = list[position].coverage


             },
             { position ->

             })
     }




 //   benefitRecycler

}

data class ExposureData(var name:String, var coverage:String)