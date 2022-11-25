package com.finalatixassignment.networks

import com.finalatixassignment.constant.ServerConstant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object RetrofitClient {

    private var retrofit: Retrofit? = null

    /**
     * Create an instance of Retrofit object
     */
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(ServerConstant.BASE_URL)
                   .client(OkHttpClient().newBuilder().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}