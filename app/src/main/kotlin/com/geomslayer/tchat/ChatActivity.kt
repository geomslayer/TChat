package com.geomslayer.tchat

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        "You are in '${intent.getStringExtra(CHAT_TITLE)}' chat now".let {
            Snackbar.make(chatLayout, it, Snackbar.LENGTH_SHORT).show()
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true
        }
        val decoration = DividerItemDecoration(this, layoutManager.orientation)
        val adapter = ChatAdapter().apply {
            dataset = createDataset()
            clickListener = { position ->
                Snackbar.make(chatLayout, "position = $position", Snackbar.LENGTH_SHORT).show()
            }
        }
        chatRecyclerView.apply { ->
            setHasFixedSize(true)
            setAdapter(adapter)
            setLayoutManager(layoutManager)
            addItemDecoration(decoration)
        }
    }

    private fun createDataset(): MutableList<MessageItem> {
        return arrayListOf<MessageItem>().apply {
            for (i in 1..20) {
                add(MessageItem("author $i", "message $i"))
            }
        }
    }

}
