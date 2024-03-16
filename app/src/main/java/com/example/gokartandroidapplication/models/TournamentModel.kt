package com.example.gokartandroidapplication.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TournamentModel(
    var id: Long = 0,
    var title: String = "",
    var description: String = "",
    var date: String = "",
    var location: String = "",
    var laps: String = ""
) : Parcelable