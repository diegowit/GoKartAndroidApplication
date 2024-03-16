package com.example.gokartandroidapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gokartandroidapplication.R
import com.example.gokartandroidapplication.main.MainApp
class GoKartListActivity : AppCompatActivity() {

    lateinit var app: MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gokartlist)
        app = application as MainApp
    }
}