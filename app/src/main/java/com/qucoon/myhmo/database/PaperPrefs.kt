package com.qucoon.myhmo.database

import android.app.Application
import android.content.Context
import io.paperdb.Paper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class PaperPrefs {
    constructor(application: Application){
        Paper.init(application)
        this.context = application
    }

    constructor(context: Context){
        Paper.init(context)
        this.context = context
    }


    companion object{
        val FIRSTNAME = "g1s"
        val LASTNAME = "g2s"
        val EMAIL = "g3s"
        val PHONE = "g4s"
        val LATITUDE = "g5s"
        val LONGITUDE = "g6S"
        val IMAGE = "g7s"
        val PUSHID="push"
        val ENROLSTATUS="Enrolstatus"
 }
    private  var context: Context
    private fun getStringFromPref(key:String):String{
        return runBlocking {
            async(Dispatchers.IO){
                Paper.book().read(key,"")
            }.await()
        }
    }

    private fun getStringFromPref(key:String,deafult:String):String{
        return runBlocking {
            async(Dispatchers.IO){
                Paper.book().read(key,deafult)
            }.await()
        }
    }

    private fun saveBooleanToPref(key:String,value:Boolean){
        runBlocking {
            withContext(Dispatchers.IO){
                Paper.book().write(key,value)
            }
        }
    }

    private fun getBooleanFromPref(key:String):Boolean{
        return runBlocking {
            async(Dispatchers.IO){
                Paper.book().read(key,false)
            }.await()
        }
    }

    private fun getBooleanFromPref(key:String,defaultvalue:Boolean):Boolean{
        return runBlocking {
            async(Dispatchers.IO){
                Paper.book().read(key,defaultvalue)
            }.await()
        }
    }


    private fun <T:Any> saveAnyToPref(key: String,data:T){
        runBlocking {
            withContext(Dispatchers.IO){
                Paper.book().write(key,data)
            }
        }
    }




    private fun <T:Any> getAnyFromPref(key:String):T{
        return runBlocking {
            async(Dispatchers.IO){
                Paper.book().read(key) as T
            }.await()
        }
    }

    private fun saveStringToPref(key:String,value:String){
        runBlocking {
            withContext(Dispatchers.IO){
                Paper.book().write(key,value)
            }
        }
    }


    fun String.getStringPref():String{
        return getStringFromPref(this)
    }

    fun String.getStringPref(default:String):String{
        return getStringFromPref(this,default)
    }

    fun String.saveStringPref(value:String){

        saveStringToPref( this,value)
    }

    fun String.getBooleanPref():Boolean{
        return getBooleanFromPref(this)
    }
    fun String.getBooleanPref(default:Boolean):Boolean{
        return getBooleanFromPref(this,default)
    }

    fun <T:Any> String.getAnyPref():T{
        return getAnyFromPref(this)
    }
    fun <T:Any> String.saveAnyPref(data:T){
        return saveAnyToPref(this,data)
    }

    fun String.saveBooleanPref(value:Boolean){
        saveBooleanToPref(this,value)
    }

}
fun PaperPrefs.savePref(key: String, value: Any){

    when(value){
        is String -> {key.saveStringPref(value)}
        is Boolean -> {key.saveBooleanPref(value)}
        else -> {
            key.saveAnyPref(value)
        }
    }
}

fun PaperPrefs.getStringPref(key: String):String{
    return key.getStringPref()
}

fun PaperPrefs.getBooleanPref(key: String):Boolean{
    return key.getBooleanPref()
}

fun PaperPrefs.getBooleanPref(key: String, defaultValue:Boolean):Boolean{
    return key.getBooleanPref(defaultValue)
}
fun <T:Any> PaperPrefs.getAnyPref(key: String):T{
    return key.getAnyPref()
}
fun String?.saveToPref(paperPrefs: PaperPrefs,key: String){
    this?.let {
        paperPrefs.savePref(key,it)
    }
}