package com.example.githubuserinfo.activity

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.githubuserinfo.R
import com.example.githubuserinfo.model.GitHubUser

class UserActivity : AppCompatActivity() {

    // Holds the user login as String.
    private lateinit var login: String

    // The GitHubUser passed in the intent.
    lateinit var gitHubUser: GitHubUser

    // UI objects
    lateinit var tvLogin: TextView
    lateinit var ivAvatar: ImageView
    lateinit var tvName: TextView
    lateinit var tvBlog: TextView
    lateinit var tvLocation: TextView
    lateinit var tvBio: TextView
    lateinit var tvPublicRepos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        // Initialize user interface objects
        initUserInterface()

        // Get the intent that started this activity.
        val intent = intent

        // Get the extras from the intent.
        val extras: Bundle? = intent.extras

        // Create the GitHubUser from the extra.
        this.gitHubUser = extras?.get("GitHubUser") as GitHubUser

        //Update the UI with GitHubUser information.
        updateUserInterface()

    }

    private fun updateUserInterface() {

        // Get all the required information from the GitHubUser
        val login: String? = this.gitHubUser.login
        val avatarURL: String? = this.gitHubUser.avatar
        val blog: String? = this.gitHubUser.blog
        val name: String? = this.gitHubUser.name
        val location: String? = this.gitHubUser.location
        val bio: String? = this.gitHubUser.bio

        // Update all the views in UserActivity with appropriate information.
        this.tvLogin.text = login
        // TODO: Load the GitHubUser avatar image.
        this.tvName.text = "Name: $name"
        this.tvBlog.text = "Blog: $blog"
        this.tvLocation.text = "Location: $location"
        this.tvBio.text = "Bio: $bio"
    }

    // Initialize all the views in UserActivity.
    private fun initUserInterface() {
        this.tvLogin = findViewById(R.id.tvLogin)
        this.ivAvatar = findViewById(R.id.ivAvatar)
        this.tvName = findViewById(R.id.tvName)
        this.tvBlog = findViewById(R.id.tvBlog)
        this.tvLocation = findViewById(R.id.tvLocation)
        this.tvBio = findViewById(R.id.tvBio)
    }
}