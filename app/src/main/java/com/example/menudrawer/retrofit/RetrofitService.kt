package com.example.menudrawer.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val URL = "https://api.spaceflightnewsapi.net/"
    val REQUEST: RetrofitRepository by lazy {
            Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitRepository::class.java)
    }
}