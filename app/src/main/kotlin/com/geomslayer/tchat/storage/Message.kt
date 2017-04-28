package com.geomslayer.tchat.storage

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import java.util.*

@Table(name = "messages", database = AppDatabase::class)
class Message : BaseModel() {

    @PrimaryKey(autoincrement = true)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "author_id")
    var authorId: Long = 0

    @Column(name = "dialog_id")
    var dialogId: Long = 0

    @Column(name = "text")
    var text: String = ""

    @Column(name = "creation_time")
    var creationTime: Calendar = Calendar.getInstance()

}