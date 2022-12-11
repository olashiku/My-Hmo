package com.qucoon.myhmo.livedata


import android.net.Uri
import com.qucoon.nibbs.utils.SingleLiveEvent

class DataPasserLiveData {
    val hasEnroledStatus = SingleLiveEvent<String>()
    val imageUri = SingleLiveEvent<Uri>()
}