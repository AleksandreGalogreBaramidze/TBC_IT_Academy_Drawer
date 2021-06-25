package com.example.menudrawer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.menudrawer.retrofit.RetrofitService
import kotlinx.coroutines.*

class DelayViewModel:ViewModel() {
    @InternalCoroutinesApi
    fun loopRequest(): Job {
        return CoroutineScope(Dispatchers.Default).launch {
            while (NonCancellable.isActive) {
                if ( RetrofitService.REQUEST.getNewsData().isSuccessful) {
                    val item = RetrofitService.REQUEST.getNewsData().body()
                }
                delay(10000)
            }
        }
    }


}