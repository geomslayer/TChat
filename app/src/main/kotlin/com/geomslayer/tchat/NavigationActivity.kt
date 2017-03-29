package com.geomslayer.tchat

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.content_navigation.*

class NavigationActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        DialogListFragment.Listener {

    companion object {
        val DEFAULT_ITEM = 0
    }

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(this, drawer, toolbar, 0, 0)
        drawer.addDrawerListener(toggle)

        navView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            navView.menu.getItem(DEFAULT_ITEM).apply {
                isChecked = true
                onNavigationItemSelected(this)
            }
        }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_dialogs -> setFragment(DialogListFragment.newInstanse())
            R.id.nav_settings -> Log.d("Tmp", "Settings")
            R.id.nav_about -> Log.d("Tmp", "About")
            R.id.nav_exit -> Log.d("Tmp", "Exit")
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.contentNavigation, fragment)
                .commit()
    }

    override fun onDialogClick(dialog: DialogItem) {
        Intent(this, ChatActivity::class.java).apply {
            putExtra(CHAT_TITLE, dialog.title)
            startActivity(this)
        }
    }

}
