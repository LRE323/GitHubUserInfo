package com.luisruiz.githubuserinfo.rest

import com.luisruiz.githubuserinfo.model.GitHubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("/users/{user}")
    fun getUser(@Path("user") user: String): Call<GitHubUser>
}