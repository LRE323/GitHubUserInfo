package com.luisruiz.githubuserinfo.rest

import com.luisruiz.githubuserinfo.model.GitHubUser
import retrofit2.Call


class Repository(private val gitHubService: GitHubService) {

    /**
     * Get a user from the network with the login provided.
     */
    fun getUser(login: String): Call<GitHubUser> {
        return gitHubService.getUser(login)
    }
}