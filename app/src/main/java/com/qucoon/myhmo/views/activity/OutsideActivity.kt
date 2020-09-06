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
import com.google.firebase.iid.FirebaseInstanceId
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

    // these are location libraries
    lateinit var  mFusedLocationClient: FusedLocationProviderClient
    lateinit var mSettingsClient: SettingsClient
    lateinit var  mLocationRequest: LocationRequest
    lateinit var  mLocationSettingsRequest: LocationSettingsRequest
    lateinit var mLocationCallback: LocationCallback
    lateinit var  mCurrentLocation: Location
    private val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 10000
    private val FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS: Long = 1000
    private val REQUEST_CHECK_SETTINGS = 100
    lateinit var mLastUpdateTime: String
    private var mRequestingLocationUpdates: Boolean? = null

    lateinit var  paperPrefs:PaperPrefs



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setContentView(R.layout.activity_outside)
        initView()
        initialzeFireBase()

    }

    fun initView(){
        paperPrefs = PaperPrefs(application)
        supportActionBar!!.hide()
     //   Utils.setStatusBarColor(this,"#5DD0AD")
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

    fun initialzeFireBase(){
        println("igothere1")
        // this is for my firebase information and the likes tho.. this would be saved into the database
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    //To do//
                    return@OnCompleteListener
                }

                // Get the Instance ID token//
                println("igothere2")

                val token = task.result!!.token
                val msg = getString(R.string.fcm_token, token)
              //  paperPref.setPushId(msg)
                println("igothere3thisismyfirebaseid"+ msg)
                paperPrefs.savePref(PaperPrefs.PUSHID,msg)
            })
    }


    fun init(){

        CheckPermissionUtil.checkLocation(this, PermissionUtil.ReqPermissionCallback { b ->
            if (b) {
                println("soigothere_fix_this_shit")

                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext)
                mSettingsClient = LocationServices.getSettingsClient(applicationContext)

                mLocationCallback = object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult?) {
                        super.onLocationResult(locationResult)
                        // location is received
                        println("soigothere1")
                        mCurrentLocation = locationResult!!.lastLocation
                        mLastUpdateTime = DateFormat.getTimeInstance().format(Date())
                        setLocation(mCurrentLocation.longitude, mCurrentLocation.latitude)
                        stopLocationUpdates()

                        //updateLocationUI();
                    }
                }

                mRequestingLocationUpdates = false
                mLocationRequest = LocationRequest()
                mLocationRequest.interval = UPDATE_INTERVAL_IN_MILLISECONDS
                mLocationRequest.fastestInterval = FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
                mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

                val builder = LocationSettingsRequest.Builder()
                builder.addLocationRequest(mLocationRequest)
                mLocationSettingsRequest = builder.build()
                startLocationUpdates()

            } else {
                println("soigothere2")


            }
        })
    }

    private fun setLocation(latitde: Double, longitude: Double) {
        Timber.e("latitude is $latitde logitude is $longitude")
        println(        "latitude is $latitde logitude is $longitude")
        paperPrefs.savePref(PaperPrefs.LATITUDE,latitde.toString())
        paperPrefs.savePref(PaperPrefs.LONGITUDE,longitude.toString())

    }
    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        mSettingsClient
            .checkLocationSettings(mLocationSettingsRequest)
            .addOnSuccessListener(this) {

                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper())


                //   updateLocationUI();
            }
            .addOnFailureListener(this) { e ->
                println("myexception$e")
                e.printStackTrace()
                val statusCode = (e as ApiException).statusCode
                when (statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->

                        try {

                            val rae = e as ResolvableApiException
                            rae.startResolutionForResult(this@OutsideActivity, REQUEST_CHECK_SETTINGS)
                        } catch (sie: IntentSender.SendIntentException) {
                            //       Log.i(TAG, "PendingIntent unable to execute request.");
                        }

                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE ->{
                        val errorMessage = "Location settings are inadequate, and cannot be " + "fixed here. Fix in Settings."
                    }
                }
            }
    }
    fun stopLocationUpdates() {
        mFusedLocationClient
            .removeLocationUpdates(mLocationCallback)
            .addOnCompleteListener(this) {}
    }


}
