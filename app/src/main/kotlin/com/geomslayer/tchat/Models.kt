package com.geomslayer.tchat

import com.geomslayer.tchat.storage.Dialog
import com.geomslayer.tchat.storage.Message

data class DialogItem(val id: Long, val title: String, val desc: String)

data class MessageItem(val author: String, val message: String, val own: Boolean)

fun Dialog.toDialogItem(): DialogItem {
    val desc = if (lastMessage == null) {
        description
    } else {
        "${lastMessage?.toMessageItem()?.author}: ${lastMessage?.text}"
    }
    return DialogItem(id, title, desc)
}

fun Message.toMessageItem(): MessageItem {
    val author = when (authorId) {
        BaseApp.userId -> "You"
        1L -> "geomslayer"
        2L -> "latko"
        3L -> "pestryakov"
        4L -> "lugo"
        else -> "someone"
    }
    return MessageItem(author, text, authorId == BaseApp.userId)
}
