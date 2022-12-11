package com.example.neptune.viewmodel

import androidx.lifecycle.*
import com.example.neptune.utils.handleException
import com.nubis.watchguard.utils.UseCaseResult
import com.qucoon.nibbs.utils.SingleLiveEvent
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlin.coroutines.CoroutineContext

open class BaseViewModel:ViewModel(), CoroutineScope,LifecycleObserver {
    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job
    val showLoading = MutableLiveData<Boolean>()
    val showError = SingleLiveEvent<String>()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }


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