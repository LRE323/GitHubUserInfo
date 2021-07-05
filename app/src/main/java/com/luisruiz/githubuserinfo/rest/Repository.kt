package com.luisruiz.githubuserinfo.rest

import com.luisruiz.githubuserinfo.model.GitHubUser
import retrofit2.Call
import retrofit2.Retrofit


class Repository {

    private val retrofit: Retrofit = APIClient.retrofit

    private val gitHubService: GitHubService = retrofit.create(GitHubService::class.java)

    /**
     * Get a user from the network with the login provided.
     */
    fun getUser(login: String): Call<GitHubUser> {
        return gitHubService.getUser(login)
    }
}