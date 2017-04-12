package com.geomslayer.tchat.chat

import android.content.Context
import android.support.v4.content.AsyncTaskLoader
import com.geomslayer.tchat.MessageItem
import java.util.*

class MessagesLoader(context: Context) : AsyncTaskLoader<ArrayList<MessageItem>>(context) {

    override fun loadInBackground(): ArrayList<MessageItem> {
        val phrases = listOf(
                "Hello!",
                "Go to the party, my friend!",
                "Well, I'm so tired... It's time to sleep, bye.",
                "Ooooh! Party!",
                "Hey, did you hear new Fifth Harmony's song? It's a bomb!",
                "Oopsy daisy, last message isn't for you :(",
                "I heard Microsoft bought Skype for 8.5 billion USD. Bimbos… why didn’t they just download it like everyone else?",
                "Hey, you just need to visit http://pxls.space, it's amazing!!!"
        )
        val rand = Random()
        val res = arrayListOf<MessageItem>().apply {
            for (i in 1..20) {
                val message = phrases[rand.nextInt(phrases.size)]
                val own = rand.nextBoolean()
                val author = if (own) "You" else "Friend"
                add(MessageItem(author, message, own))
            }
        }
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