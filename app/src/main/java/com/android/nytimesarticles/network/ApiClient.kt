package com.android.nytimesarticles.network

import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class ApiClient {
    private var RETROFIT: Retrofit? = null


    fun getRetrofit(): Retrofit? {
        if (RETROFIT == null) {
            val okHttpClient = initHttpClient()
            RETROFIT = Retrofit.Builder()
                .baseUrl("http://api.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return RETROFIT
    }

      fun getNyTimesApi(): NyTimesApi {
        return getRetrofit()!!.create(NyTimesApi::class.java!!)
    }

    private fun initHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .readTimeout(30000, TimeUnit.MILLISECONDS)
            .connectTimeout(30000, TimeUnit.MILLISECONDS)
            .addInterceptor(LoggingInterceptor())
            .build()
    }

    internal class LoggingInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val t1 = System.nanoTime()
            Log.d(
                "Retrofit Request", String.format(
                    "Sending request %s on %s%n%s",
                    request.url(), Gson().toJson(request.body()), chain.connection(),
                    request.headers()
                )
            )

            val response = chain.proceed(request)
            val t2 = System.nanoTime()
            Log.d(
                "Retrofit Response", String.format(
                    "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6, response.headers()
                )
            )

            return response
        }
    }

    private fun gson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    }

}