package com.geomslayer.tchat

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

        val message = "${intent.getStringExtra(LoginActivity.USERNAME)} successfully logged in!"
        Snackbar.make(mainLayout, message, Snackbar.LENGTH_LONG).show()

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, layoutManager.orientation)
        val adapter = DialogAdapter(createDataset(), object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Snackbar.make(mainLayout, "position = $position", Snackbar.LENGTH_SHORT).show()
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
