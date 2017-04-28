package com.geomslayer.tchat.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.geomslayer.tchat.BaseApp
import com.geomslayer.tchat.NavigationActivity
import com.geomslayer.tchat.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginFragment.LoginListener {

    private var progress: ProgressDialog? = null
    private var loginFragment: LoginFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener { onLogin() }

        loginFragment = supportFragmentManager.findFragmentByTag(LoginFragment.TAG) as LoginFragment?
        if (loginFragment == null) {
            loginFragment = LoginFragment()
            supportFragmentManager.beginTransaction().add(loginFragment, LoginFragment.TAG).commit()
        }

        if (loginFragment?.inProgress ?: false) {
            showProgress()
        }
        loginFragment?.success?.let { onResult(it) }
    }

    fun onLogin() {
        showProgress()
        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString()
        LoginTask(loginFragment).execute(arrayOf(username, password))
    }

    fun showProgress() {
        progress = ProgressDialog.show(this, "Checking...", null)
    }

    fun hideProgress() {
        progress?.dismiss()
    }

    fun startNextScreen() {
        Intent(this, NavigationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(this)
        }
    }

    override fun onResult(success: Boolean) {
        hideProgress()
        if (success) {
            val username = usernameEditText.text.toString().trim()
            BaseApp.userId = when (username) {
                "geomslayer" -> 1
                "latko" -> 2
                "pestryakov" -> 3
                "lugo" -> 4
                else -> 5
            }
            BaseApp.username = username
            startNextScreen()
        } else {
            Snackbar.make(loginLayout, R.string.incorrect, Snackbar.LENGTH_LONG).show()
        }
    }

}
