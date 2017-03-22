package com.geomslayer.tchat

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val CHAT_TITLE = "chat_title"
    }

    val dataset = createDataset()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val message = "${intent.getStringExtra(LoginActivity.USERNAME)} successfully logged in!"
        Snackbar.make(mainLayout, message, Snackbar.LENGTH_LONG).show()

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, layoutManager.orientation)
        val adapter = DialogAdapter(dataset, object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val chatIntent = Intent(this@MainActivity, ChatActivity::class.java)
                chatIntent.putExtra(CHAT_TITLE, dataset[position].title)
                startActivity(chatIntent)
            }
        })
        dialogRecyclerView.setHasFixedSize(true)
        dialogRecyclerView.layoutManager = layoutManager
        dialogRecyclerView.adapter = adapter
        dialogRecyclerView.addItemDecoration(decoration)
    }

    private fun createDataset(): List<DialogItem> {
        val res = arrayListOf<DialogItem>()
        for (i in 1..20) {
            res.add(DialogItem("title $i", "desc $i"))
        }
        return res
    }

}
