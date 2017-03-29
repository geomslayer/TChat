package com.geomslayer.tchat

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity(), ChatListFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        "You are in '${intent.getStringExtra(CHAT_TITLE)}' chat now".let {
            Snackbar.make(chatActivityLayout, it, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onMessageClick(message: MessageItem) {
        Snackbar.make(chatActivityLayout, message.toString(), Snackbar.LENGTH_SHORT).show()
    }

}
