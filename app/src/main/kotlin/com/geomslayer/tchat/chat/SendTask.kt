package com.geomslayer.tchat.chat

import android.os.AsyncTask
import com.geomslayer.tchat.BaseApp
import com.geomslayer.tchat.storage.Dialog
import com.geomslayer.tchat.storage.Dialog_Table
import com.geomslayer.tchat.storage.Message
import com.raizlabs.android.dbflow.sql.language.SQLite
import java.util.*

class SendTask(val chatId: Long) : AsyncTask<String, Unit, Unit>() {

    override fun doInBackground(vararg args: String) {
        for (message in args) {
            val savedMessage = Message().apply {
                authorId = BaseApp.userId
                dialogId = chatId
                text = message
                creationTime = Calendar.getInstance()
                save()
            }
            val dialog: Dialog? = SQLite.select()
                    .from(Dialog::class.java)
                    .where(Dialog_Table.id.eq(chatId))
                    .querySingle()
            dialog?.lastMessage = savedMessage
            dialog?.save()
        }
    }

}