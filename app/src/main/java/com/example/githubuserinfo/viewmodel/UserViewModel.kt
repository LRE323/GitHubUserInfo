package com.example.githubuserinfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserinfo.model.GitHubUser
import com.example.githubuserinfo.rest.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    // The GitHubUser retrieved from the login info.
    val userLiveData = MutableLiveData<GitHubUser?>()

    // The Repository that accesses the network.
    private val repository = Repository()

    /**
     * Attempts to retrieve the GitHubUser from the login provided.
     *
     * If the response is successful, it will update the value of userLiveData.
     */
    fun submitGitHubUser(login: String) {

        // Call for the desired GitHubUser.
        val call: Call<GitHubUser> = repository.getUser(login)

        // Send the request.
        call.enqueue(object : Callback<GitHubUser> {

            /**
             * Called if the network request is successful.
             */
            override fun onResponse(call: Call<GitHubUser>, response: Response<GitHubUser>) {

                // If the response is successful.
                if (response.isSuccessful) {

                    // Get the GitHubUser from the response.
                    val tempUser: GitHubUser? = response.body()

                    // Set gitHubUser.
                    userLiveData.value = tempUser
                }
            }

            /**
             * Called if the network request fails.
             */
            override fun onFailure(call: Call<GitHubUser>, t: Throwable) {
            }

        })
    }
}