package com.example.githubuserinfo.model

import com.google.gson.annotations.SerializedName

data class GitHubUser(

    @SerializedName("login")
    val login: String?,

    @SerializedName("avatar_url")
    val avatar: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("blog")
    val blog: String?,

    @SerializedName("location")
    val location: String?,

    @SerializedName("bio")
    val bio: String?,

    @SerializedName("public_repos")
    val publicRepos: String?

)