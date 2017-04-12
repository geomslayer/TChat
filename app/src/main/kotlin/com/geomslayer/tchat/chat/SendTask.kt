package com.geomslayer.tchat.chat

import android.os.AsyncTask

class SendTask : AsyncTask<String, Void, Void?>() {

    override fun doInBackground(vararg args: String): Void? {
        val message = args[0]
        // send message to the server

        return null
    }

}