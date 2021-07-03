package com.example.githubuserinfo.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserinfo.model.GitHubUser
import com.example.githubuserinfo.rest.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    // The GitHubUser retrieved from the login info.
    var gitHubUser: GitHubUser? = null

    // LiveData for the GitHubUser
    val userLiveData = MutableLiveData<GitHubUser?>()

    // The Repository that accesses the network.
    private val repository = Repository()


    /**
     * Attempts to retrieve the GitHubUser from the login provided.
     *
     * If the response is successful, it will update the value of userLiveData.
     */
    fun submitGitHubUser(login: String, context: Context) {

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
                    gitHubUser = response.body()

                    // Update the LiveData value to the GitHubUser.
                    userLiveData.value = gitHubUser

                // If the response is unsuccessful.
                } else {
                    Toast.makeText(context, UNSUCCESSFUL_RESPONSE, Toast.LENGTH_SHORT).show()
                }

            }

            /**
             * Called if the network request fails.
             */
            override fun onFailure(call: Call<GitHubUser>, t: Throwable) {
                Toast.makeText(context, FAILED_REQUEST, Toast.LENGTH_SHORT).show()
            }

        })
    }

    companion object {
        private const val UNSUCCESSFUL_RESPONSE: String = "User not found. Please try again."
        private const val FAILED_REQUEST: String =
            "Failed request. Please check your internet connection."
    }
}