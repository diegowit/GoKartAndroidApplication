package com.example.gokartandroidapplication.activities

import android.view.Menu
import com.example.gokartandroidapplication.main.MainApp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.example.gokartandroidapplication.models.GoKartModel
import com.example.gokartandroidapplication.databinding.ActivityGokartBinding
import android.view.MenuItem
import timber.log.Timber.i
import com.example.gokartandroidapplication.R



class GoKartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGokartBinding
    var gokart = GoKartModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGokartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.title = title
        setSupportActionBar(binding.topAppBar)


        app = application as MainApp
        i("Go kart Activity started...")


        if (intent.hasExtra("gokart_edit")) {
            gokart   = intent.extras?.getParcelable("gokart_edit")!!
            binding.DriverName.setText(gokart.name)
            binding.DriverGender.setText(gokart.gender)
            binding.CarModel.setText(gokart.carModel)
        }



        binding.btnAdd.setOnClickListener() {
            gokart.name = binding.DriverName.text.toString()
            gokart.carModel = binding.CarModel.text.toString()
            gokart.gender = binding.DriverGender.text.toString()
            if (gokart.name.isNotEmpty()) {
                app.gokarts.create(gokart.copy())

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
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_gokart, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                setResult(RESULT_CANCELED)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}