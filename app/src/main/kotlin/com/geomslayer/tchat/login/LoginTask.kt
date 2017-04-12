package com.geomslayer.tchat.login

import android.os.AsyncTask
import java.lang.ref.WeakReference

class LoginTask(fragment: LoginFragment?) : AsyncTask<Array<String>, Void, Boolean>() {

    val loginFragment = WeakReference(fragment)

    override fun onPreExecute() {
        super.onPreExecute()
        loginFragment.get()?.apply { inProgress = true }
    }

    override fun doInBackground(vararg args: Array<String>): Boolean {
        try {
            Thread.sleep(500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return true
    }

    override fun onPostExecute(result: Boolean) {
        loginFragment.get()?.apply {
            success = result
            inProgress = false
        }
    }

}
