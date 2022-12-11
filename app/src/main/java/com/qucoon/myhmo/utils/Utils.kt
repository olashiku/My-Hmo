package com.example.neptune.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.util.Patterns
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.crashlytics.internal.common.CommonUtils
import java.io.File
import java.io.IOException

object Utils {



    fun delaytime(time: Long, myCallback: () -> Unit){
        Handler().postDelayed({
            myCallback.invoke()
        }, time)
    }


    @Throws(IOException::class)
    public fun getImageFile(): File? {
        val imageFileName = "JPEG_" + System.currentTimeMillis() + "_"
        val storageDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera")
        storageDir.mkdirs()
        System.out.println(storageDir.getAbsolutePath())
        if (storageDir.exists()) println("File exists") else println("File not exists")
        val file: File = File.createTempFile(imageFileName, ".jpg", storageDir)
        // currentPhotoPath = "file:" + file.getAbsolutePath()
        return file
    }



    fun setStatusBarColor(activity: Activity, colorcode: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window =activity!!.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor(colorcode)
        }
    }

     fun getTetxt(editText: EditText):String{
         return editText.getString()
     }

    fun checkIsEmpty(edittext: EditText, context:Context?, view: View?):Boolean{
        if(edittext.text.isEmpty()){
            Toast.makeText(context,"Please insert values in the required field", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
    }


    fun checkValidName(edittext: EditText, context:Context?, view: View?):Boolean{
        if(edittext.text.length < 2){
            Toast.makeText(context,"Please use a valid name", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
    }

    fun checkvalidemail(edittext: EditText, context:Context?, view: View?):Boolean{
        val valid = Patterns.EMAIL_ADDRESS.matcher(edittext.text.toString()).matches()
        if(!valid){
            Toast.makeText(context,"Please use a valid email", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
    }

    fun checkvalidphone(edittext: EditText, context:Context?, view: View?):Boolean{
        if(edittext.text.length < 9){
            Toast.makeText(context,"Please use a valid phone", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
    }


    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun checkpinmatch(pin1: EditText, pin2: EditText, context: Context): Boolean{
        if ( pin1.text.toString() != pin2.text.toString()){
            Toast.makeText(context, "Your password does not match", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }

    }
}