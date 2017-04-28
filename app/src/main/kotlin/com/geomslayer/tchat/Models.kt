package com.geomslayer.tchat

import com.geomslayer.tchat.storage.Dialog
import com.geomslayer.tchat.storage.Message

data class DialogItem(val title: String, val desc: String)

data class MessageItem(val author: String, val message: String, val own: Boolean)

fun Dialog.toDialogItem(): DialogItem {
    return DialogItem(title, description)
}

fun Message.toMessageItem(): MessageItem {
    val author = when (authorId) {
        1L -> "geomslayer"
        2L -> "latko"
        3L -> "pestryakov"
        4L -> "lugo"
        else -> "someone"
    }
    return MessageItem(author, text, authorId == BaseApp.userId)
}
