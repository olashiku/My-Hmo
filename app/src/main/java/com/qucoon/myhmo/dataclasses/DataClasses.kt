package com.qucoon.myhmo.dataclasses

import java.io.Serializable

data class OnboardDataClass(val image:Int, val text1:String,val text2:String,val text3:String):Serializable
data class  DashboardData(val image:Int, val text1:String,val text2:String):Serializable
data class SettingsClass(val name:String)
data class ContactItemDataClass(val image:Int, val name:String)
data class ProfileDataClass(val title:String,val name:String)
