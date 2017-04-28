package com.geomslayer.tchat

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.geomslayer.tchat.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
