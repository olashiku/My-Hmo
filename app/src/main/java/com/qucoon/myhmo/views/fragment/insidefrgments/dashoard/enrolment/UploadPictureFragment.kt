package com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.baurine.permissionutil.PermissionUtil
import com.example.neptune.utils.withArguments
import com.qucoon.keystonemobile.utils.CheckPermissionUtil
import com.qucoon.myhmo.R
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.myhmo.database.savePref
import com.qucoon.myhmo.livedata.DataPasserLiveData
import com.qucoon.myhmo.popups.ImagePickerFragment
import com.qucoon.myhmo.views.activity.MainActivity
import com.qucoon.royalexchange.ui.base.BaseFragment
import inc.qucoon.nativeveezah.utils.S3Uploader
import kotlinx.android.synthetic.main.fragment_data_final.*
import org.koin.java.KoinJavaComponent
import java.io.IOException


class UploadPictureFragment : BaseFragment(),ImagePickerFragment.ImagePickerFragmentCallback {

     var imageURL:String =""
    lateinit var credentialsProvider: AWSCredentials
    private val dataPasserLiveData = KoinJavaComponent.inject(DataPasserLiveData::class.java)


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
        watchAndObserveLiveData()
    }

     fun watchAndObserveLiveData(){
         dataPasserLiveData.value.imageUri.observe(viewLifecycleOwner, Observer {

             try {

                 val bitmap = MediaStore.Images.Media.getBitmap(activity!!.getContentResolver(), it)
                 convertImageAndSave(bitmap,it.path)


             } catch (e: IOException) {
                 e.printStackTrace()
             }

         })
     }

     fun initView(){
         credentialsProvider =  BasicAWSCredentials(getString(R.string.privateaccesskey1),getString(R.string.secretkey1))


         var hasvalise=  CheckPermissionUtil.hasCameraPermission(activity!!)



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
            Toast.makeText(context,"Kindly accept the permission before you proceed", Toast.LENGTH_SHORT).show()
        } else {
            mFragmentNavigation.openBottomSheet(ImagePickerFragment())
        }
    }




     fun convertImageAndSave(bitmap: Bitmap, imageUri: String?){
         imageURL = "https://myhmo-revisited-imagebucket.s3.us-east-2.amazonaws.com/${paperPrefs.getStringPref(PaperPrefs.CUSTOMERID)}.${imageUri!!.substringAfterLast('.', "")}"
         imageData.setImageBitmap(bitmap)
         uploadImage(imageUri!!)
     }


    private fun uploadImage(imageURL:String){
        println("thisistheimageURL" + imageURL)
        S3Uploader.upload(context!!,credentialsProvider,"myhmo-revisited-imagebucket",imageURL,
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

    override fun performAction(action: String) {
        when(action){
            "camera"->{
                (activity as MainActivity).openCamera()
            }
            "gallary"->{
                (activity as MainActivity).openGallary()

            }
        }

    }

}