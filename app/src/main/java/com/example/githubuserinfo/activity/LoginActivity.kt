package com.example.githubuserinfo.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.githubuserinfo.R

class LoginActivity : AppCompatActivity() {

    // UI variables
    private lateinit var etLoginInput: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSampleUser: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Create the UI objects
        initUserInterfaceObjects()

        // Set the onClickListeners
        setOnClickListeners()
    }

    private fun setOnClickListeners() {

        this.btnLogin.setOnClickListener {

            // Get the login.
            val login = etLoginInput.text.toString()

            // Create a new intent.
            val intent = createIntent(login)

            // Start the Activity.
            startActivity(intent)
        }

        this.btnSampleUser.setOnClickListener {

            // Create the login.
            val login = "LRE323"

            // Create a new intent.
            val intent = createIntent(login)

            // Start the Activity.
            startActivity(intent)
        }

    }

    private fun initUserInterfaceObjects() {
        this.etLoginInput = findViewById(R.id.loginInput)
        this.btnLogin = findViewById(R.id.btnLogin)
        this.btnSampleUser = findViewById(R.id.btnOpenSampleUserGitHub)
    }

    private fun createIntent(login: String): Intent {
        return Intent(this, UserActivity::class.java).putExtra("login", login)
    }
}