package com.luisruiz.githubuserinfo.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    // Github base URL
    private const val BASE_URL = "https://api.github.com/"

    // The Gson converter for Retrofit
    private val gsonConverter: GsonConverterFactory = GsonConverterFactory.create()

    // The instance of Retrofit.
    val retrofit: Retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverter)
            .build()

}