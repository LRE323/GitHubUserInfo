package com.example.githubuserinfo.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import retrofit2.Response

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

) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeString(avatar)
        parcel.writeString(name)
        parcel.writeString(blog)
        parcel.writeString(location)
        parcel.writeString(bio)
        parcel.writeString(publicRepos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GitHubUser> {

        override fun createFromParcel(parcel: Parcel): GitHubUser {
            return GitHubUser(parcel)
        }

        fun getUser(response: Response<GitHubUser>): GitHubUser {
            // Get all the GitHubUser attributes from the response.
            val tempLogin: String? = response.body()?.login
            val tempAvatar: String? = response.body()?.avatar
            val tempName: String? = response.body()?.name
            val tempBlog: String? = response.body()?.blog
            val tempLocation: String? = response.body()?.location
            val tempBio: String? = response.body()?.bio
            val tempPublicRepos: String? = response.body()?.publicRepos

            return GitHubUser(tempLogin, tempAvatar, tempName, tempBlog, tempLocation, tempBio, tempPublicRepos)
        }

        override fun newArray(size: Int): Array<GitHubUser?> {
            return arrayOfNulls(size)
        }
    }
}