package com.qucoon.myhmo.popups

import android.app.Notification
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import com.amazonaws.mobileconnectors.s3.transfermanager.Upload
import com.qucoon.myhmo.BuildConfig
import com.qucoon.myhmo.R
import com.qucoon.myhmo.databinding.FragmentImagePickerBinding
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.royalexchange.ui.base.BaseActivity
import com.qucoon.royalexchange.ui.base.BaseBottomSheetFragment
import com.qucoon.royalexchange.ui.base.BaseFragment
import java.io.File
import java.io.IOException


class ImagePickerFragment : BaseBottomSheetFragment() {

    var binding : FragmentImagePickerBinding? = null
    interface ImagePickerFragmentCallback{
        fun performAction(action:String)
    }

    lateinit var  imagePickerFragmentCallback: ImagePickerFragmentCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        imagePickerFragmentCallback = parentFragment as ImagePickerFragmentCallback
        binding = FragmentImagePickerBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClick()
    }

     fun initOnClick(){
         binding!!.cameraButton.setOnClickListener {
             dismiss()
             imagePickerFragmentCallback.performAction("camera")
         }
         binding!!.gallaryButton.setOnClickListener {
             dismiss()
             imagePickerFragmentCallback.performAction("gallary")
            }
     }



     companion object{
         val CAMERACODE = 1
         val GALLARYCODE = 2
     }


    override fun onDestroy() {
        super.onDestroy()
        binding = null

    }
}