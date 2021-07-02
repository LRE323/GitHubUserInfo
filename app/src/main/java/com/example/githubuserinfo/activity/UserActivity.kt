package com.example.githubuserinfo.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubuserinfo.R
import com.example.githubuserinfo.model.GitHubUser
import com.example.githubuserinfo.rest.Repository
import com.example.githubuserinfo.viewmodelfactory.UserViewModelFactory
import com.example.githubuserinfo.viewmodel.UserViewModel

class UserActivity : AppCompatActivity() {

    // The ViewModel for this Activity.
    private lateinit var viewModel: UserViewModel

    // UI objects
    private lateinit var tvLogin: TextView
    private lateinit var ivAvatar: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvBlog: TextView
    private lateinit var tvLocation: TextView
    private lateinit var tvBio: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        // Initialize user interface objects
        initUserInterface()

        // Initialize the ViewModel.
        initViewModel()

        // Observe the LiveData.
        initLiveDataObservation()
    }

    private fun updateUserInterface(gitHubUser: GitHubUser) {

        // Get all the required information from the GitHubUser
        val login: String? = gitHubUser.login
        val avatarURL: String? = gitHubUser.avatar
        val blog: String? = gitHubUser.blog
        val name: String? = gitHubUser.name
        val location: String? = gitHubUser.location
        val bio: String? = gitHubUser.bio

        // Update all the TextViews with appropriate information.
        this.tvLogin.text = login
        this.tvName.text = getString(R.string.textview_name_text, name)
        this.tvBlog.text = getString(R.string.textview_blog_text, blog)
        this.tvLocation.text = getString(R.string.textview_location_text, location)
        this.tvBio.text = getString(R.string.textview_bio_text, bio)

        // Load avatar image.
        Glide.with(this).load(avatarURL).into(ivAvatar)

    }

    /**
     * Initializes all the views.
     */
    private fun initUserInterface() {
        this.tvLogin = findViewById(R.id.tvLogin)
        this.ivAvatar = findViewById(R.id.ivAvatar)
        this.tvName = findViewById(R.id.tvName)
        this.tvBlog = findViewById(R.id.tvBlog)
        this.tvLocation = findViewById(R.id.tvLocation)
        this.tvBio = findViewById(R.id.tvBio)
    }

    /**
     * Initializes the ViewModel.
     */
    private fun initViewModel() {

        // Create a LoginViewModelFactory.
        val factory = UserViewModelFactory(Repository())

        // Create a new ViewModelProvider.
        val viewModelProvider = ViewModelProvider(this, factory)

        // Create a new ViewModel.
        viewModel = viewModelProvider.get(UserViewModel::class.java)

    }

    /**
     * Initializes all the code necessary for LiveData observation.
     */
    private fun initLiveDataObservation() {

        // Get the login from the intent.
        val login: String = intent.getStringExtra("login").toString()

        // Create the observer that updates the UI.
        val observer = Observer<GitHubUser?> { gitHubUser ->

            //Update the UI with GitHubUser information.
            updateUserInterface(gitHubUser)
        }

        // Observe the LiveData
        viewModel.userLiveData.observe(this, observer)

        // Submit the login so the ViewModel can attempt to retrieve it from the network.
        viewModel.submitGitHubUser(login, this)
    }
}