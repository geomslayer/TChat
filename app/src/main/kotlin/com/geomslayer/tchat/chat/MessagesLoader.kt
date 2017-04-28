package com.geomslayer.tchat.chat

import android.content.Context
import android.support.v4.content.AsyncTaskLoader
import com.geomslayer.tchat.MessageItem
import com.geomslayer.tchat.storage.Message
import com.geomslayer.tchat.storage.Message_Table
import com.geomslayer.tchat.toMessageItem
import com.raizlabs.android.dbflow.sql.language.SQLite
import java.util.*

class MessagesLoader(context: Context, val dialogId: Long) :
        AsyncTaskLoader<ArrayList<MessageItem>>(context) {

    override fun loadInBackground(): ArrayList<MessageItem> {
//        val phrases = listOf(
//                "Hello!",
//                "Go to the party, my friend!",
//                "Well, I'm so tired... It's time to sleep, bye.",
//                "Ooooh! Party!",
//                "Hey, did you hear new Fifth Harmony's song? It's a bomb!",
//                "Oopsy daisy, last message isn't for you :(",
//                "I heard Microsoft bought Skype for 8.5 billion USD. Bimbos… why didn’t they just download it like everyone else?",
//                "Hey, you just need to visit http://pxls.space, it's amazing!!!"
//        )

        val res = SQLite.select()
                .from(Message::class.java)
                .where(Message_Table.dialog_id.eq(dialogId))
                .orderBy(Message_Table.creation_time, false)
                .queryList()
                .mapTo(ArrayList<MessageItem>()) { it.toMessageItem() }

        try {
            Thread.sleep(300)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return res
    }

    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }

}