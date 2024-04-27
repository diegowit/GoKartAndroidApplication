package com.example.gokartandroidapplication.models

import timber.log.Timber
import com.example.gokartandroidapplication.utils.Utilities.isValidListIndex
var lastId = 0L
internal fun getId() = lastId++
class GoKartMemStore : GoKartStorage {

    private val gokarts = ArrayList<GoKartModel>()

    override fun create(gokart: GoKartModel) {
        gokart.id = getId()
        gokarts.add(gokart)
        logAll()
    }
  /*  // Check if the index is a valid index for the karts list
    fun isValidIndex(index: Int): Boolean {
        return isValidListIndex(index, gokarts)
    }

    // Find a kart by its index in the karts list
    fun findKart(index: Int): GoKartModel? {
        return if (isValidIndex(index)) {
            gokarts[index]
        } else null
    }

    fun formatListString(kartsToFormat: List<GoKartModel>): String =
        kartsToFormat
            .joinToString(separator = "\n") { kart ->
                gokarts.indexOf(kart).toString() + ": " + kart.toString()
            }
*/

       override fun update(gokart: GoKartModel) {
        val foundGoKart: GoKartModel? = gokarts.find { p -> p.id == gokart.id }
        if (foundGoKart != null) {
            foundGoKart.name = gokart.name
            foundGoKart.gender = gokart.gender
            foundGoKart.carModel = gokart.carModel
            foundGoKart.image = gokart.image
            foundGoKart.lat = gokart.lat
            foundGoKart.lng = gokart.lng
            foundGoKart.zoom = gokart.zoom
            logAll()
        }
    }

    override fun delete(gokart: GoKartModel) {
       gokarts.remove(gokart)
    }

    override fun findById(id:Long) : GoKartModel? {
        val foundGokart: GoKartModel? = gokarts.find { it.id == id }
        return foundGokart
    }

    private fun logAll() {
        gokarts.forEach { Timber.i("$it") }
    }

    override fun findAll(): List<GoKartModel> {
        return gokarts
    }
    override fun findByName(name:String) : GoKartModel? {
        val foundGoKart: GoKartModel? = gokarts.find { it.name == name }
        return foundGoKart
    }


    /*   override fun searchByName(searchString: String) =
           formatListString(gokarts.filter { it.name.contains(searchString, ignoreCase = true) })
           */

}

