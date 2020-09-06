package com.qucoon.keystonemobile.utils

import android.Manifest
import android.Manifest.permission
import android.Manifest.permission.*
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.baurine.permissionutil.PermissionUtil
import com.qucoon.myhmo.R


object CheckPermissionUtil {
    fun isPermissionGranted(permission:String,context:Context):Boolean =
            ContextCompat.checkSelfPermission(
                    context,
                    permission
            ) == PackageManager.PERMISSION_GRANTED

    fun hasSMSPermision(activity: Activity):Boolean{
        return   PermissionUtil.hasPermission(activity,READ_SMS)
    }

    fun hasLocationPermission(activity: Activity):Boolean{
        return   PermissionUtil.hasPermission(activity,LOCATION_HARDWARE)
    }
    private val LOCATION_PERMISSION_REQ_CODE = 200
    private val WRITE_SD_REQ_CODE = 201
    private val PICK_CONTACT = 34
    private val PICK_CAMERA = 334
    private val PERMISSIONS_REQUEST_READ_SMS = 100
    private val  PERMISSION_CHECK_TELEPHONE=120


    fun checkLocation(
        activity: Activity,
        callback: PermissionUtil.ReqPermissionCallback
    ) {
        PermissionUtil.checkPermission(
            activity,
            ACCESS_FINE_LOCATION,
            LOCATION_PERMISSION_REQ_CODE,
            activity.getText(R.string.location_req_reason),
            activity.getText(R.string.location_reject_msg),
            callback
        )
    }

    fun checkTelephone(
        activity: Activity,
        callback: PermissionUtil.ReqPermissionCallback
    ) {
        PermissionUtil.checkPermission(
            activity,
            CALL_PHONE,
            PERMISSION_CHECK_TELEPHONE,
            activity.getText(R.string.phone_req_reason),
            activity.getText(R.string.phone_reject_reason),
            callback
        )
    }


    @JvmStatic
    fun readSMS(
            activity: Activity,
            callback: PermissionUtil.ReqPermissionCallback
    ) {
        PermissionUtil.checkPermission(
                activity,
                READ_SMS,
                PERMISSIONS_REQUEST_READ_SMS,
                activity.getText(R.string.sms_req_reason),
                activity.getText(R.string.location_reject_msg),
                callback
        )
    }


    @JvmStatic
    fun camera(
        activity: Activity,
        callback: PermissionUtil.ReqPermissionCallback
    ) {
        PermissionUtil.checkPermission(
            activity,
            CAMERA,
            PICK_CAMERA,
            activity.getText(R.string.camera_req_reason),
            activity.getText(R.string.camera_reject_msg),
            callback
        )
    }

    fun checkWriteSd(
        activity: Activity,
        callback: PermissionUtil.ReqPermissionCallback
    ) {
        PermissionUtil.checkPermission(
            activity,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            WRITE_SD_REQ_CODE,
            "We need write external storage permission to save your location to file",
            "We can't save your location to file without storage permission",
            callback
        )
    }

    fun checkContacts(
        activity: Activity,
        callback: PermissionUtil.ReqPermissionCallback
    ) {
        PermissionUtil.checkPermission(
            activity,
            Manifest.permission.READ_CONTACTS,
            PICK_CONTACT,
            activity.getText(R.string.contact_req_reason)
            ,
            activity.getText(R.string.contact_reject_msg)
            ,
            callback
        )
    }
}
