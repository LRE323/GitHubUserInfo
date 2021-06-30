package com.example.githubuserinfo.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.githubuserinfo.R
import com.example.githubuserinfo.model.GitHubUser
import com.example.githubuserinfo.rest.APIClient
import com.example.githubuserinfo.rest.GitHubUserEndPoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    // UI variables
    lateinit var etLoginInput: EditText
    lateinit var btnLogin: Button
    lateinit var login: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Create the UI objects
        initUserInterfaceObjects()
    }

    /**
     * onClicked for btnLogin
     */
    fun login(view: View?) {

        // Get the login.
        this.login = etLoginInput.text.toString()

        // Boolean value for a valid login.
        val validLogin = isLoginValid()

        // If the login is valid.
        if (validLogin) {

            // Create a new Intent that navigates to UserActivity.
            val intent = Intent(this, UserActivity::class.java)

            // Put the login in the intent.
            intent.putExtra("login", this.login)

            // Start UserActivity
            startActivity(intent)
        }

    }

    private fun isLoginValid(): Boolean {

        var bool: Boolean = false

        // Get an instance of Retrofit.
        val retrofit = APIClient.retrofit

        // Generate an implementation of the GitHubUserEndPoints interface.
        val apiService = retrofit.create(GitHubUserEndPoints::class.java)

        // Call for the desired GitHub user.
        val call: Call<GitHubUser> = apiService.getUser(this.login)

        // Send the request.
        call.enqueue(object : Callback<GitHubUser> {

            // If the request is successful.
            override fun onResponse(call: Call<GitHubUser>, response: Response<GitHubUser>) {

                // Check if the response was successful.
                val successfulResponse = response.isSuccessful

                if (successfulResponse) {
                    Toast.makeText(this@LoginActivity, "Successful response", Toast.LENGTH_SHORT).show()
                    bool = true

                    // Create a new Intent that navigates to UserActivity.
                    val intent = Intent(this@LoginActivity, UserActivity::class.java)

                    // Get a GitHubUser from the response.
                    val gitHubUser: GitHubUser = GitHubUser.getUser(response)

                    // Put the GitHubUser in the intent.
                    intent.putExtra("GitHubUser", gitHubUser)

                    // Start UserActivity
                    startActivity(intent)


                } else {
                    Toast.makeText(this@LoginActivity, "Unsuccessful response", Toast.LENGTH_SHORT).show()
                }
            }

            // If the request fails.
            override fun onFailure(call: Call<GitHubUser>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Request failed", Toast.LENGTH_SHORT).show()
            }
        })
        return bool
    }

    private fun initUserInterfaceObjects() {
        this.etLoginInput = findViewById(R.id.usernameInput)
        this.btnLogin = findViewById(R.id.loginButton)
    }
}