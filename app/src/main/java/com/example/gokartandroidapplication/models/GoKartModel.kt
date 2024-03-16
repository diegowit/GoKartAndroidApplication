package com.example.gokartandroidapplication.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class GoKartModel(
    var id: Long = 0,
    var name: String = "",
    var gender: String = "",
    var carModel: String = "",
    var image: Uri = Uri.EMPTY
    ) : Parcelable
