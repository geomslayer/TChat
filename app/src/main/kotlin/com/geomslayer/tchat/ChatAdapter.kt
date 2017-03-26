package com.geomslayer.tchat

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_chat_message.view.*

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    var dataset: MutableList<MessageItem> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var clickListener: (Int) -> Unit = {}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDialog(dataset[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_chat_message, parent, false)
        return ViewHolder(view, clickListener)
    }

    override fun getItemCount() = dataset.size

    class ViewHolder(view: View, listener: (Int) -> Unit) : RecyclerView.ViewHolder(view) {

        init {
            itemView.setOnClickListener { listener(adapterPosition) }
        }

        fun bindDialog(messageItem: MessageItem) = with(itemView) {
            authorTextView.text = messageItem.author
            messageTextView.text = messageItem.message
        }

    }
}