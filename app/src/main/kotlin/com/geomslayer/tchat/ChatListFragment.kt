package com.geomslayer.tchat

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_chat_list.view.*

class ChatListFragment : Fragment() {

    var listener: Listener? = null
    lateinit var fragmentView: View

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ChatListFragment.Listener
                ?: throw RuntimeException("$context must implement ") // TODO
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentView = inflater.inflate(R.layout.fragment_chat_list, container, false)
        initRecyclerView()
        return fragmentView
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity).apply {
            reverseLayout = true
        }
        val decoration = DividerItemDecoration(activity, layoutManager.orientation)
        val adapter = ChatAdapter().apply {
            dataset = createDataset()
            clickListener = { position -> listener?.onMessageClick(dataset[position]) }
        }
        fragmentView.chatRecyclerView.apply { ->
            setHasFixedSize(true)
            setAdapter(adapter)
            setLayoutManager(layoutManager)
            addItemDecoration(decoration)
        }
    }

    private fun createDataset(): MutableList<MessageItem> {
        return arrayListOf<MessageItem>().apply {
            for (i in 1..20) {
                add(MessageItem("author $i", "message $i"))
            }
        }
    }

    interface Listener {
        fun onMessageClick(message: MessageItem)
    }

}