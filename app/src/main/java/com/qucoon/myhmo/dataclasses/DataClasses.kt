package com.qucoon.myhmo.dataclasses

import java.io.Serializable

data class OnboardDataClass(val image:Int, val text1:String,val text2:String,val text3:String):Serializable
data class  DashboardData(val image:Int, val text1:String,val text2:String):Serializable
data class MenuData(val image:Int,val title:String, val desc:String)
data class SettingsClass(val name:String)
