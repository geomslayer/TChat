package com.geomslayer.tchat.storage

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.ForeignKey
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import java.util.*

@Table(name = "dialogs", database = AppDatabase::class)
class Dialog : BaseModel() {

    @PrimaryKey(autoincrement = true)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "title")
    var title: String = ""

    @Column(name = "description")
    var description: String = ""

    @Column(name = "creation_time")
    var creationTime: Calendar = Calendar.getInstance()

    @ForeignKey(tableClass = Message::class)
    var lastMessage: Message? = null

}