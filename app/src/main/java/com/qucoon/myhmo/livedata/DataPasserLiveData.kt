package com.qucoon.myhmo.livedata


import com.qucoon.nibbs.utils.SingleLiveEvent

class DataPasserLiveData {
    val hasEnroledStatus = SingleLiveEvent<String>()
}