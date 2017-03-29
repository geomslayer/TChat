package com.geomslayer.tchat

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class LoginActivity : AppCompatActivity(), LoginFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onLogin(username: String) {
        Intent(this, NavigationActivity::class.java).apply {
            putExtra(USERNAME, username)
            startActivity(this)
        }
    }

}
