package com.android.nytimesarticles.network

import com.android.nytimesarticles.models.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by omprakash8080 on 18/9/2017.
 */

interface NyTimesApi {

    @GET("/svc/mostpopular/v2/mostviewed/all-sections/7.json")
    abstract fun getArticlesResponse(@Query("api-key") key: String): Call<ArticlesResponse>

}