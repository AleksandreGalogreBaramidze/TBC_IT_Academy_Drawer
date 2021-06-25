package com.example.menudrawer.retrofit

import com.example.menudrawer.model.SpaceNewsModel
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitRepository {
    @GET("v3/articles")
    suspend fun getNewsData(): Response<List<SpaceNewsModel>>
}