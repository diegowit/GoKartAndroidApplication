package com.example.gokartandroidapplication.utils
import com.example.gokartandroidapplication.models.GoKartModel
object Utilities {
    @JvmStatic
    fun formatListString(kartsToFormat: List<GoKartModel>): String =
        kartsToFormat
            .joinToString(separator = "\n") { gokart -> "$gokart" }

    @JvmStatic
    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }

    @JvmStatic
    fun validRange(numberToCheck: Int, min: Int, max: Int): Boolean {
        return numberToCheck in min..max
    }
}
