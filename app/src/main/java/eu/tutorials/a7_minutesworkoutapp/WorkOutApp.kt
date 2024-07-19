package eu.tutorials.a7_minutesworkoutapp

import android.app.Application

class WorkOutApp:Application() {
    val db by lazy {
        HistoryDatabase.getInstance(this)
    }
}