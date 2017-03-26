package com.geomslayer.tchat

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener { doLogin() }
    }

    private fun doLogin() {
        Intent(this, MainActivity::class.java).apply {
            putExtra(USERNAME, usernameEditText.text.toString())
            startActivity(this)
        }
    }

}
