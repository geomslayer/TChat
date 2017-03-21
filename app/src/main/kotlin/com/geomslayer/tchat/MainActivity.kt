package com.geomslayer.tchat

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val message = "${intent.getStringExtra(LoginActivity.USERNAME)} successfully logged in!"
        Snackbar.make(mainLayout, message, Snackbar.LENGTH_LONG).show()
    }

}
