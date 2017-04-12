package com.geomslayer.tchat.chat

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geomslayer.tchat.MessageItem
import com.geomslayer.tchat.R
import kotlinx.android.synthetic.main.item_chat_message.view.*
import java.lang.UnsupportedOperationException

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    companion object {
        val OWN = 1
        val ANOTHER = 0
    }

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
        val layout = when (viewType) {
            OWN -> R.layout.item_chat_own_message
            ANOTHER -> R.layout.item_chat_message
            else -> throw UnsupportedOperationException("Wrong view type!")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(view, clickListener)
    }

    override fun getItemCount() = dataset.size

    override fun getItemViewType(position: Int): Int {
        return if (dataset[position].own) OWN else ANOTHER
    }

    fun addMessageItem(message: MessageItem) {
        dataset.add(0, message)
        notifyItemInserted(0)
    }

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