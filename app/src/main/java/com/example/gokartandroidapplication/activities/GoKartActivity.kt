package com.example.gokartandroidapplication.activities

import android.view.Menu
import com.example.gokartandroidapplication.main.MainApp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.example.gokartandroidapplication.models.GoKartModel
import com.example.gokartandroidapplication.databinding.ActivityGokartBinding
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import timber.log.Timber.i
import com.example.gokartandroidapplication.R
import com.example.gokartandroidapplication.helpers.showImagePicker
import android.content.Intent
import android.net.Uri
import com.example.gokartandroidapplication.models.Location
import com.squareup.picasso.Picasso
import com.example.gokartandroidapplication.activities.DatePickerFragment

class GoKartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGokartBinding
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var imageIntentLauncher: ActivityResultLauncher<Intent>

    var gokart = GoKartModel()
    lateinit var app: MainApp
    var location = Location(52.245696, -7.139102, 15f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGokartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.title = title
        setSupportActionBar(binding.topAppBar)

        // Initialize the MainApp and other variables
        app = application as MainApp
        var edit = false

        /***** Logging *****/
        i(getString(R.string.gokart_activity_started))

        // Check if this activity is opened for editing a GoKartModel
        if (intent.hasExtra("gokart_edit")) {
            edit = true
            gokart = intent.extras?.getParcelable("gokart_edit")!!
            // Set the existing data to the UI elements
            binding.DriverName.setText(gokart.name)
            binding.DriverGender.setText(gokart.gender)
            binding.CarModel.setText(gokart.carModel)
            binding.btnAdd.text = getString(R.string.save_gokart)
            // Load the image if it exists
            Picasso.get()
                .load(gokart.image)
                .into(binding.placemarkImage)
            // Change the text of chooseImage button if an image is already selected
            if (gokart.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_gokart_image)
            }
        }

        // Handle click event of add button
        binding.btnAdd.setOnClickListener() {
            gokart.name = binding.DriverName.text.toString()
            gokart.gender = binding.DriverGender.text.toString()
            gokart.carModel = binding.CarModel.text.toString()
            if (gokart.name.isNotEmpty()) {
                if (edit) {
                    app.gokarts.update(gokart.copy())
                } else {
                    app.gokarts.create(gokart.copy())
                }
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar.make(it, getString(R.string.enter_gokart_title),
                    Snackbar.LENGTH_LONG).show()
            }
        }

        // Handle click event of chooseImage button
        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }
        registerImagePickerCallback()

        // Handle click event of placemarkLocation button
        binding.placemarkLocation.setOnClickListener {
            val launcherIntent = Intent(this, MapActivity::class.java)
                .putExtra("location", location)
            mapIntentLauncher.launch(launcherIntent)
        }
        registerMapCallback()
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

    // Register callback for image picker result
    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            gokart.image = result.data!!.data!!
                            Picasso.get()
                                .load(gokart.image)
                                .into(binding.placemarkImage)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

    // Register callback for map activity result
    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Location ${result.data.toString()}")
                            location = result.data!!.extras?.getParcelable("location")!!
                            i("Location == $location")
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}