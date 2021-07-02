package com.example.githubuserinfo.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.githubuserinfo.R

class LoginActivity : AppCompatActivity() {

    // UI variables
    private lateinit var etLoginInput: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Create the UI objects
        initUserInterfaceObjects()

    }

    /**
     * onClicked for btnLogin
     */
    @Suppress("UNUSED_PARAMETER")
    fun login(view: View) {

        // Get the login.
        val login = etLoginInput.text.toString()

        // Create a new intent.
        val intent = createIntent(login)

        // Start the Activity.
        startActivity(intent)

    }

    private fun initUserInterfaceObjects() {
        this.etLoginInput = findViewById(R.id.loginInput)
        this.btnLogin = findViewById(R.id.loginButton)
    }

    @Suppress("UNUSED_PARAMETER")
    fun openLuisGitHub(view: View) {

        // Create the login.
        val login = "LRE323"

        // Create a new intent.
        val intent = createIntent(login)

        // Start the Activity.
        startActivity(intent)
    }

    private fun createIntent(login: String): Intent {
        return Intent(this, UserActivity::class.java).putExtra("login", login)
    }
}