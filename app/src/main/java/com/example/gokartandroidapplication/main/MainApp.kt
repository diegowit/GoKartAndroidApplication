package com.example.gokartandroidapplication.main

import android.app.Application
import com.example.gokartandroidapplication.models.GoKartModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp: Application() {
    val gokarts = ArrayList<GoKartModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Go Kart App Started")

        gokarts.add(GoKartModel("Checo", "Male","Ferrari"))
        gokarts.add(GoKartModel("Verstappen", "Male","Red bull"))
    }
}