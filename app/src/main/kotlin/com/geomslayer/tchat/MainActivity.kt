package com.geomslayer.tchat

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        "${intent.getStringExtra(USERNAME)} successfully logged in!".apply {
            Snackbar.make(mainLayout, this, Snackbar.LENGTH_LONG).show()
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, layoutManager.orientation)
        val adapter = DialogAdapter().apply {
            dataset = createDataset()
            clickListener = { position ->
                Intent(this@MainActivity, ChatActivity::class.java).apply {
                    putExtra(CHAT_TITLE, dataset[position].title)
                    startActivity(this)
                }
            }
        }
        dialogRecyclerView.apply {
            setHasFixedSize(true)
            setLayoutManager(layoutManager)
            setAdapter(adapter)
            addItemDecoration(decoration)
        }
    }

    private fun createDataset(): MutableList<Models> {
        return arrayListOf<Models>().apply {
            for (i in 1..20) {
                add(Models("title $i", "desc $i"))
            }
        }
    }

}
