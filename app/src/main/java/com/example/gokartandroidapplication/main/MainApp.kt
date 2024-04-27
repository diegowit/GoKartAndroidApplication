package com.example.gokartandroidapplication.main

import android.app.Application
import com.example.gokartandroidapplication.models.GoKartJSONStore
import com.example.gokartandroidapplication.models.GoKartMemStore
import com.example.gokartandroidapplication.models.GoKartStorage
import com.example.gokartandroidapplication.models.TournamentMemStore
import com.example.gokartandroidapplication.models.TournamentStorage
import timber.log.Timber
import timber.log.Timber.i

class MainApp: Application() {
    lateinit var gokarts : GoKartStorage
    lateinit var tournaments : TournamentStorage

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        gokarts = GoKartJSONStore(applicationContext)
        i("Go Kart App Started")

        //gokarts.add(GoKartModel("Checo", "Male","Ferrari"))
        //gokarts.add(GoKartModel("Verstappen", "Male","Red bull"))
    }

}