package com.example.gokartandroidapplication.activities


import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.gokartandroidapplication.databinding.ActivityGokartlistBinding
//import com.example.gokartandroidapplication.databinding.CardGoKartBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gokartandroidapplication.R
import com.example.gokartandroidapplication.main.MainApp

class GoKartListActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityGokartlistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGokartlistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_gokartlist)
        app = application as MainApp




    }
}