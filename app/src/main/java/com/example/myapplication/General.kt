package com.example.myapplication

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class General : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val realmName = "Sameer_project"


        val configuration = RealmConfiguration.Builder()
            .schemaVersion(14)
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(false)
            .allowQueriesOnUiThread(true)
            .name(realmName)
            .build()

        Realm.setDefaultConfiguration(configuration)

    }
}