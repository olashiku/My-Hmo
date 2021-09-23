package com.example.neptune.viewmodel

import androidx.lifecycle.*
import com.example.neptune.utils.handleException
import com.nubis.watchguard.utils.UseCaseResult
import com.qucoon.nibbs.utils.SingleLiveEvent
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlin.coroutines.CoroutineContext

open class BaseViewModel:ViewModel(), CoroutineScope,LifecycleObserver {
    // Coroutine's background job
    private val job = Job()
    // Define default thread for Coroutine as Main and add job
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job
    val showLoading = MutableLiveData<Boolean>()
    val showError = SingleLiveEvent<String>()
    val showSessionTimeOut= SingleLiveEvent<String>()
    override fun onCleared() {
        super.onCleared()
        // Clear our job when the linked activity is destroyed to avoid memory leaks
        job.cancel()
    }
    fun validateInput(vararg data:Pair<String,String>):Boolean{
        var error = ""
        data.forEach { field ->
            if(field.first.isNullOrEmpty()){
                if(error == "") error += "${field.second} is missing" else  ", ${field.second} is missing"
            }
        }
        if(error != ""){
            showError.value = error
            return false
        }
        return true
    }

    //         apiRequest(request,dashboardRepossitory::enrolUser,enroluserResponse,{it.responsemessage})
    fun <R:Any,T:Any> apiRequest(request:R, apiCall:suspend (request:R)-> UseCaseResult<T>, observer:SingleLiveEvent<T>, getError:(response:T) -> String){
        showLoading.value = true
        launch {
            val response = withContext(IO){apiCall(request)}
            showLoading.value = false
            when(response){
                is UseCaseResult.Success -> observer.value = response.data
                is UseCaseResult.FailedAPI -> showError.value = getError(response.data)
                is UseCaseResult.Error -> showError.value = response.exception.handleException()
            }
        }
    }



    fun <R:Any,T:Any> apiRequest(request:R, apiCall:suspend (request:R)-> UseCaseResult<T>, observer:SingleLiveEvent<T>, getError:(response:T) -> String,onSuccessHandler:(response:T) -> Unit){
        showLoading.value = true
        launch {
            val response = withContext(IO){apiCall(request)}
            showLoading.value = false
            when(response){
                is UseCaseResult.Success -> {
                    onSuccessHandler(response.data)
                    observer.value = response.data
                }
                is UseCaseResult.FailedAPI -> showError.value = getError(response.data)
                is UseCaseResult.Error -> showError.value = response.exception.handleException()
            }
        }
    }

}
fun <T> SingleLiveEvent<T>.observeUnit(owner: LifecycleOwner,action:(T?)->Unit){
    this.observe(owner, Observer { action(it) })
}
fun <T> SingleLiveEvent<T>.observeChange(owner: LifecycleOwner,action:(T)->Unit){
    this.observe(owner, Observer { action(it) })
}
fun <T> LiveData<T>.observeChange(owner: LifecycleOwner,action:(T)->Unit){
    this.observe(owner, Observer { action(it) })
}
fun <T> MutableLiveData<T>.observeChange(owner: LifecycleOwner,action:(T)->Unit){
    this.observe(owner, Observer { action(it) })
}