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

        val message = "You are in '${intent.getStringExtra(MainActivity.CHAT_TITLE)}' chat now"
        Snackbar.make(chatLayout, message, Snackbar.LENGTH_SHORT).show()

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        val decoration = DividerItemDecoration(this, layoutManager.orientation)
        val adapter = DialogAdapter(createDataset(), object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Snackbar.make(chatLayout, "position = $position", Snackbar.LENGTH_SHORT).show()
            }
        })
        chatRecyclerView.setHasFixedSize(true)
        chatRecyclerView.layoutManager = layoutManager
        chatRecyclerView.adapter = adapter
        chatRecyclerView.addItemDecoration(decoration)
    }

    private fun createDataset(): List<DialogItem> {
        val res = arrayListOf<DialogItem>()
        for (i in 1..20) {
            res.add(DialogItem("author $i", "message $i"))
        }
        return res
    }

}
