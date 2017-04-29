package com.geomslayer.tchat.chat

import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.geomslayer.tchat.CHAT_ID
import com.geomslayer.tchat.CHAT_TITLE
import com.geomslayer.tchat.MessageItem
import com.geomslayer.tchat.R
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.*

class ChatActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<ArrayList<MessageItem>> {

    companion object {
        val MESSAGE_LOADER = 0
    }

    private lateinit var chatAdapter: ChatAdapter
    private lateinit var chatLayoutManager: LinearLayoutManager
    private var chatId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        chatId = intent.getLongExtra(CHAT_ID, 0)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra(CHAT_TITLE)

        sendFieldView.setOnMessageSendListener { message ->
            val text = message.trim()
            SendTask(chatId).execute(text)
            chatAdapter.addMessageItem(MessageItem("You", text, true))
            chatLayoutManager.scrollToPosition(0)
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        chatLayoutManager = LinearLayoutManager(this)
        chatLayoutManager.reverseLayout = true

        chatAdapter = ChatAdapter()
        supportLoaderManager.initLoader(MESSAGE_LOADER, null, this)

        chatRecyclerView.apply { ->
            setHasFixedSize(true)
            adapter = chatAdapter
            layoutManager = chatLayoutManager
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<ArrayList<MessageItem>> {
        return MessagesLoader(this, chatId)
    }

    override fun onLoaderReset(loader: Loader<ArrayList<MessageItem>>) {
        chatAdapter.dataset.clear()
    }

    override fun onLoadFinished(loader: Loader<ArrayList<MessageItem>>, data: ArrayList<MessageItem>) {
        data.addAll(0, chatAdapter.dataset)
        chatAdapter.dataset = data
    }

    override fun onBackPressed() {
        onNavigateUp()
    }
}
