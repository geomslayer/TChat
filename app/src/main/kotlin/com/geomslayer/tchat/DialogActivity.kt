package com.geomslayer.tchat

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_dialog.*

class DialogActivity : AppCompatActivity(), DialogListFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        setSupportActionBar(toolbar)

        "${intent.getStringExtra(USERNAME)} successfully logged in!".apply {
            Snackbar.make(dialogActivityLayout, this, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onDialogClick(dialog: DialogItem) {
        Intent(this, ChatActivity::class.java).apply {
            putExtra(CHAT_TITLE, dialog.title)
            startActivity(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> {
                startActivity(Intent(this, AboutActivity::class.java))
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

}
