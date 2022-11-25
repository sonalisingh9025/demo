package com.finalatixassignment.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finalatixassignment.models.ResponseNameData
import com.finalatixassignment.networks.AppRepository
import com.finalatixassignment.utils.AppUtility
import kotlinx.coroutines.*
import retrofit2.Response


class MainActivityViewModel : ViewModel() {


    private val scope = CoroutineScope(Dispatchers.IO)
    private var job: Job? = null

    private val _names = MutableLiveData<ArrayList<ResponseNameData>>()
    val nameData: LiveData<ArrayList<ResponseNameData>> = _names

    val errors = MutableLiveData<String>()
    val error: LiveData<String> = errors


     fun getNameListApi() {
         job = scope.launch {
             try {
                 val response = AppRepository().getNameList()

                 when (response.isSuccessful) {
                     true -> {
                         _names.postValue(response.body())
                     }
                     false -> {
                         errors.postValue(AppUtility.errorHandle(response as Response<Any>))
                     }
                 }
             } catch (e: Exception) {
                 errors.postValue(e.message)
             }
         }
     }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
        scope.coroutineContext.cancel()
    }

}