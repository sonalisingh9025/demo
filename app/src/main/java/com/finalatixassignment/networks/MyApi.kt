package com.finalatixassignment.networks

import com.finalatixassignment.models.ResponseNameData
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {

    @GET("marvel")
    suspend fun getNameList(
    ): Response<ArrayList<ResponseNameData>>





    companion object {
        operator fun invoke(): MyApi {
            return RetrofitClient.retrofitInstance?.create(MyApi::class.java)!!
        }
    }

}