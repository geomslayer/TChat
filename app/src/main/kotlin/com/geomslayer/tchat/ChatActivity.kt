package com.geomslayer.tchat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity(), ChatFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        "You are in '${intent.getStringExtra(CHAT_TITLE)}' chat now".let {
//            Snackbar.make(chatScreen, it, Snackbar.LENGTH_SHORT).show()
//        }
    }

    override fun onMessageClick(message: MessageItem) {
//        Snackbar.make(chatScreen, message.toString(), Snackbar.LENGTH_SHORT).show()
    }

}
