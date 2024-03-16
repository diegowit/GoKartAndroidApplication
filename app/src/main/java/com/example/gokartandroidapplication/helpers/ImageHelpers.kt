package com.example.gokartandroidapplication.helpers


import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.example.gokartandroidapplication.R

fun showImagePicker(intentLauncher : ActivityResultLauncher<Intent>) {
    var chooseFile = Intent(Intent.ACTION_OPEN_DOCUMENT)
    chooseFile.type = "image/*"
    chooseFile = Intent.createChooser(chooseFile, R.string.select_gokart_image.toString())
    intentLauncher.launch(chooseFile)
}