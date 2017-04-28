package com.geomslayer.tchat.chat

import android.os.AsyncTask
import com.geomslayer.tchat.BaseApp
import com.geomslayer.tchat.storage.Message
import java.util.*

class SendTask : AsyncTask<String, Unit, Unit>() {

    override fun doInBackground(vararg args: String) {
        for (message in args) {
            Message().apply {
                authorId = BaseApp.userId
                text = message
                creationTime = Calendar.getInstance()
                save()
            }
        }
    }

}