package com.finalatixassignment.networks

import com.finalatixassignment.models.ResponseNameData
import retrofit2.Response

class AppRepository {


    suspend fun getNameList(): Response<ArrayList<ResponseNameData>> {
        return MyApi().getNameList()
    }


}