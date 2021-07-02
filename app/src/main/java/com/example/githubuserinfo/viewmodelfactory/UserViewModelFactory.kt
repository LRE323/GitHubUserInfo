package com.example.githubuserinfo.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubuserinfo.rest.Repository
import com.example.githubuserinfo.viewmodel.UserViewModel
import java.lang.IllegalArgumentException


class UserViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        // Determine whether modelClass is either the same as, or is a superclass or superinterface of,
        // LoginViewModel.
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel() as T
        }
        throw IllegalArgumentException("ViewModel not found")
    }
}