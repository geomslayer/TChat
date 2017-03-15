package com.geomslayer.tchat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Picasso.with(this)
                .load("http://www.freeiconspng.com/uploads/like-icons-png-7.png")
                .into(findViewById(R.id.mainImageView) as ImageView)
    }

    fun onChange(view: View) {
        when (view.id) {
            R.id.btn_top -> {
                btn_bottom.toggle()
            }
            R.id.btn_bottom -> {
                btn_top.toggle()
            }
        }
    }
}
