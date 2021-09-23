package com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.example.neptune.utils.withArguments
import com.github.dhaval2404.imagepicker.ImagePicker
import com.qucoon.myhmo.R
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.myhmo.database.savePref
import com.qucoon.royalexchange.ui.base.BaseFragment
import inc.qucoon.nativeveezah.utils.S3Uploader
import kotlinx.android.synthetic.main.fragment_data_final.*
import java.io.IOException


class UploadPictureFragment : BaseFragment() {

     var imageURL:String =""
    lateinit var credentialsProvider: AWSCredentials


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_data_final, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnCLick()
        initView()
    }

     fun initView(){
         credentialsProvider =  BasicAWSCredentials(getString(R.string.privateaccesskey1),getString(R.string.secretkey1))

     }

     fun initOnCLick(){
         imageData.setOnClickListener {
             ChangeProfileImage()
         }

         submitButton.setOnClickListener {

             if(imageURL.isNotEmpty()){
                 paperPrefs.savePref(PaperPrefs.IMAGE,imageURL)
                 mFragmentNavigation.pushFragment(CompleteProfileFragment().withArguments(
                     "imageurl" to imageURL
                 ))
             }

         }
     }



    fun ChangeProfileImage() {

        val permissionCheck = ContextCompat.checkSelfPermission(context!!, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permissionCheck == PackageManager.PERMISSION_DENIED) {


            ImagePicker.with(this)
                .crop(1f, 1f)                //Crop Square image(Optional)
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start()

        } else {

            if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            ) {
            } else {

                ImagePicker.with(this)
                    .crop(1f, 1f)                //Crop Square image(Optional)
                    .compress(1024)            //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                    .start()

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK && requestCode == ImagePicker.REQUEST_CODE) {

            println("thisismyimageurl" + ImagePicker.getFilePath(data)!!)
            val fileUri = Uri.parse(ImagePicker.getFilePath(data))
            val imageUri = data!!.getData()
            println("thisismyimageurl$fileUri")


            try {

                val bitmap = MediaStore.Images.Media.getBitmap(activity!!.getContentResolver(), imageUri)
                convertImageAndSave(bitmap, ImagePicker.getFilePath(data))


            } catch (e: IOException) {
                e.printStackTrace()
            }


        }

    }


     fun convertImageAndSave(bitmap: Bitmap, imageUri: String?){
         imageURL = "https://myhmoimagebucket.s3.us-east-2.amazonaws.com/${paperPrefs.getStringPref(PaperPrefs.CUSTOMERID)}.${imageUri!!.substringAfterLast('.', "")}"
         imageData.setImageBitmap(bitmap)
         uploadImage(imageUri!!)
     }



    private fun uploadImage(imageURL:String){
        println("thisistheimageURL" + imageURL)
        S3Uploader.upload(context!!,credentialsProvider,"myhmoimagebucket",imageURL,
            paperPrefs.getStringPref(PaperPrefs.CUSTOMERID)+"."+imageURL.substringAfterLast('.', "")).setTransferListener(
            object : TransferListener {
                override fun onProgressChanged(
                    id: Int,
                    bytesCurrent: Long,
                    bytesTotal: Long
                ) {
                }
                override fun onStateChanged(id: Int, state: TransferState?) {
                    if (state == TransferState.COMPLETED) {
                        Log.e("UPLOAD","SUCCESS")
                        //https://finagenetics.s3-us-west-2.amazonaws.com/helloworld.png
                        //x    backgroundRepository.uploadImageWorker("https://veezahprofileimages.s3-us-west-2.amazonaws.com/${paperPref.getStringPref(PaperPref.PHONE_NUMBER)}.png")
                    } else if (state == TransferState.FAILED) {
                        Log.e("UPLOAD","FAILED")
                    }
                }
                override fun onError(id: Int, ex: java.lang.Exception?) {
                    println(ex)
                }
            })
    }

    }