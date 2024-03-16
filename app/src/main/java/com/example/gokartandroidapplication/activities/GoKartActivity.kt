package com.example.gokartandroidapplication.activities

import com.example.gokartandroidapplication.main.MainApp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.example.gokartandroidapplication.models.GoKartModel
import com.example.gokartandroidapplication.databinding.ActivityGokartBinding
import timber.log.Timber
import timber.log.Timber.i

class GoKartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGokartBinding
    var gokart = GoKartModel()
    lateinit var app: MainApp
    var gokarts = ArrayList<GoKartModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGokartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp
        i("Placemark Activity started...")


        binding.btnAdd.setOnClickListener() {
            gokart.name = binding.DriverName.text.toString()
            gokart.carModel = binding.CarModel.text.toString()
            gokart.gender = binding.DriverGender.text.toString()
            if (gokart.name.isNotEmpty()) {
                app!!.gokarts.add(gokart.copy())
                i("add Button Pressed: ${gokart.name}")
                for (i in app!!.gokarts.indices)
                {
                    i("GoKart[$i]:${app!!.gokarts[i]}")
                }
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar
                    .make(it,"Please complete all fields", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}