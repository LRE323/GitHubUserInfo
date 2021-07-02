package com.example.githubuserinfo.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubuserinfo.R
import com.example.githubuserinfo.model.GitHubUser
import com.example.githubuserinfo.viewmodel.UserViewModel
import com.example.githubuserinfo.viewmodelfactory.UserViewModelFactory

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
    private lateinit var tvPublicRepos: TextView
    private lateinit var btnViewProfile: Button
    private lateinit var btnViewBlog: Button

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

    /**
     * Updates the user interface and other info when the LiveData that is being observed changes.
     */
    private fun onUpdate(gitHubUser: GitHubUser) {

        // Get all the required information from the GitHubUser
        val login: String = gitHubUser.login ?: "No login found"
        val avatarURL: String? = gitHubUser.avatar
        val blog: String = gitHubUser.blog ?: "No blog found"
        val name: String = gitHubUser.name ?: "No name found"
        val location: String = gitHubUser.location ?: "No location found"
        val bio: String = gitHubUser.bio ?: "No bio found"
        val publicRepos: String =
            gitHubUser.publicRepos ?: "Number of public repositories not found"

        // Update all the TextViews with appropriate information.
        this.tvLogin.text = login
        this.tvName.text = getString(R.string.textview_name_text, name)
        this.tvBlog.text = getString(R.string.textview_blog_text, blog)
        this.tvLocation.text = getString(R.string.textview_location_text, location)
        this.tvBio.text = getString(R.string.textview_bio_text, bio)
        this.tvPublicRepos.text = getString(R.string.texview_public_repos_text, publicRepos)

        // Display the buttons.
        this.btnViewProfile.visibility = View.VISIBLE
        this.btnViewBlog.visibility = View.VISIBLE

        // Load avatar image.
        Glide.with(this).load(avatarURL).into(ivAvatar)
    }

    /**
     * Initializes all the views.
     */
    private fun initUserInterface() {

        // Initialize all the TextViews
        this.tvLogin = findViewById(R.id.tvLogin)
        this.ivAvatar = findViewById(R.id.ivAvatar)
        this.tvName = findViewById(R.id.tvName)
        this.tvBlog = findViewById(R.id.tvBlog)
        this.tvLocation = findViewById(R.id.tvLocation)
        this.tvBio = findViewById(R.id.tvBio)
        this.tvPublicRepos = findViewById(R.id.tvPublicRepos)

        // Initialize the buttons.
        this.btnViewProfile = findViewById(R.id.btnViewProfile)
        this.btnViewBlog = findViewById(R.id.btnViewBlog)

        // Initially set the visibility of the buttons to invisible
        this.btnViewProfile.visibility = View.INVISIBLE
        this.btnViewBlog.visibility = View.INVISIBLE
    }

    /**
     * Initializes the ViewModel.
     */
    private fun initViewModel() {

        // Create a LoginViewModelFactory.
        val factory = UserViewModelFactory()

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

        // Create the observer.
        val observer = Observer<GitHubUser?> { gitHubUser ->

            //Update with GitHubUser information.
            onUpdate(gitHubUser)
        }

        // Observe the LiveData
        viewModel.userLiveData.observe(this, observer)

        // Submit the login so the ViewModel can attempt to retrieve it from the network.
        viewModel.submitGitHubUser(login, this)
    }

    /**
     * Opens the user's profile in a web browser.
     */
    @Suppress("UNUSED_PARAMETER")
    fun viewProfile(view: View) {

        // Get the profile URL.
        val url: String? = viewModel.gitHubUser?.profileURL

        // Attempt to open the URL.
        launchWebBrowser(url)
    }

    /**
     * Opens the user's blog in a web browser.
     */
    @Suppress("UNUSED_PARAMETER")
    fun viewBlog(view: View) {

        // Get the blog URL.
        val url: String? = viewModel.gitHubUser?.blog

        // Attempt to open the URL.
        launchWebBrowser(url)
    }

    private fun launchWebBrowser(url: String?) {

        // If the URL is valid.
        if (URLUtil.isValidUrl(url)) {

            // Create a new web intent.
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

            // Open a web browser.
            startActivity(webIntent)
        } else {
            // Don't do anything.
            Toast.makeText(this, "Invalid link", Toast.LENGTH_SHORT).show()
        }
    }
}