package com.example.gokartandroidapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber
import timber.log.Timber.i

class GoKartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gokart)

        Timber.plant(Timber.DebugTree())
        i("Go Kart Activity started..")
    }
}