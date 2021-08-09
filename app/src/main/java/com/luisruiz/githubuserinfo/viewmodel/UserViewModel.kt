package com.luisruiz.githubuserinfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisruiz.githubuserinfo.model.GitHubUser
import com.luisruiz.githubuserinfo.rest.Repository
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class UserViewModel(private val repository: Repository) : ViewModel() {

    // The GitHubUser retrieved from the login info.
    var gitHubUser: GitHubUser? = null

    // LiveData for the GitHubUser
    val gitHubUserLiveData = MutableLiveData<GitHubUser?>()

    // The LiveData for the result of the GitHubUser request.
    var requestResultMessage = MutableLiveData<String>()

    /**
     * Attempts to retrieve the GitHubUser from the login provided.
     *
     * If the response is successful, it will update the LiveData for the GitHubUser.
     */
    fun submitGitHubUser(login: String) {

        // Create a new coroutine that will execute the network request. All coroutines must run in
        // a scope. A CoroutineScope manages one or more related coroutines.
        viewModelScope.launch() {

            try {

                // Get the response.
                val response = repository.getUser(login).awaitResponse()

                if (response.isSuccessful) {

                    // Get the GitHubUser from the response.
                    gitHubUser = response.body()

                    // Update the LiveData value to the GitHubUser.
                    gitHubUserLiveData.value = gitHubUser

                } else {

                    // Update the LiveData for the request result.
                    requestResultMessage.value = UNSUCCESSFUL_RESPONSE_MESSAGE
                }
            } catch (e: Exception) {

                // Update the LiveData for the request result.
                requestResultMessage.value = FAILED_REQUEST_MESSAGE
            }

        }
    }

    companion object {

        // Message used if the network response is unsuccessful.
        private const val UNSUCCESSFUL_RESPONSE_MESSAGE: String =
            "User not found. Please try again."

        // Message used if the network request fails.
        private const val FAILED_REQUEST_MESSAGE: String =
            "Failed request. Please check your internet connection."

    }
}