package com.geomslayer.tchat

import android.app.Application
import com.geomslayer.tchat.storage.Dialog
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.sql.language.SQLite

class BaseApp : Application() {

    companion object {
        var userId: Long = 0
        var username: String = ""
    }

    override fun onCreate() {
        super.onCreate()
        FlowManager.init(FlowConfig.Builder(this)
                .openDatabasesOnInit(true).build())

        if (SQLite.select().from(Dialog::class.java).count() == 0L) {
            Dialog().apply {
                title = "TFS Trends 2017"
                description = "Chat for fintech trends course"
                save()
            }
            Dialog().apply {
                title = "TFS Android 2017"
                description = "Chat for tinkoff android course"
                save()
            }
            Dialog().apply {
                title = "Kotlin Community"
                description = "Kotlin chat"
                save()
            }
        }

    }
}