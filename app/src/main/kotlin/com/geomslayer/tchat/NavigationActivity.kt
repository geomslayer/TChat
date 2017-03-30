package com.geomslayer.tchat

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.content_navigation.*
import kotlinx.android.synthetic.main.nav_header_navigation.view.*

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
        navView.getHeaderView(0).usernameTextView.text = intent.getStringExtra(USERNAME)

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
            R.id.nav_settings -> setFragment(SettingsFragment.newInstance())
            R.id.nav_about -> setFragment(AboutFragment.newInstance())
            R.id.nav_exit -> logout()
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
                .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
                .replace(R.id.contentNavigation, fragment)
                .commit()
    }

    private fun logout() {
        Intent(this, LoginActivity::class.java).let {
            startActivity(it)
        }
    }

    override fun onDialogClick(dialog: DialogItem) {
        Intent(this, ChatActivity::class.java).apply {
            putExtra(CHAT_TITLE, dialog.title)
            startActivity(this)
        }
    }

}
