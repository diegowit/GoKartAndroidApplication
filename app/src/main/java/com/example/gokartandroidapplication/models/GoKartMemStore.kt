package com.example.gokartandroidapplication.models

import timber.log.Timber
var lastId = 0L
internal fun getId() = lastId++
class GoKartMemStore : GoKartStorage {

    val gokarts = ArrayList<GoKartModel>()

    override fun create(gokart: GoKartModel) {
        gokart.id = getId()
        gokarts.add(gokart)
        logAll()
    }


       override fun update(gokart: GoKartModel) {
        val foundGoKart: GoKartModel? = gokarts.find { p -> p.id == gokart.id }
        if (foundGoKart != null) {
            foundGoKart.name = gokart.name
            foundGoKart.gender = gokart.gender
            foundGoKart.carModel = gokart.carModel
            logAll()
        }
    }


    private fun logAll() {
        gokarts.forEach { Timber.i("$it") }
    }

    override fun findAll(): List<GoKartModel> {
        return gokarts
    }
}