package com.luisruiz.githubuserinfo.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luisruiz.githubuserinfo.rest.APIClient.retrofit
import com.luisruiz.githubuserinfo.rest.GitHubService
import com.luisruiz.githubuserinfo.rest.Repository
import com.luisruiz.githubuserinfo.viewmodel.UserViewModel

abstract class UserViewModelFactory : ViewModelProvider.Factory {

    /*abstract fun <T : ViewModel?> create(modelClass: Class<T>): T {

        // Determine whether modelClass is either the same as, or is a superclass or superinterface of,
        // LoginViewModel.
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {

            // Create the GitHubService.
            val gitHubService: GitHubService = retrofit.create(GitHubService::class.java)

            // Create the repository.
            val repository = Repository(gitHubService)

            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel not found")
    }*/


}