package com.example.neptune.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast

object Utils {



    fun delaytime(time:Long, myCallback: () -> Unit){
        Handler().postDelayed({
    myCallback.invoke()
        },time)
    }





    fun setStatusBarColor(activity: Activity, colorcode:String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window =activity!!.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor(colorcode)
        }
    }

     fun getTetxt(editText: EditText):String{
         return editText.getString()
     }

    fun checkpinmatch(pin1: EditText, pin2: EditText,context: Context): Boolean{
        if ( pin1.text.toString() != pin2.text.toString()){
            Toast.makeText(context,"Your password does not match", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }

    }
}