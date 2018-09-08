package com.example.can_c.todolist

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class EnabledOffline:Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}