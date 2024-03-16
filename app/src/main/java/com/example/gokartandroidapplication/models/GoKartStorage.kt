package com.example.gokartandroidapplication.models

import timber.log.Timber

interface GoKartStorage {
    fun findAll(): List<GoKartModel>
    fun create(gokart: GoKartModel)
}

class GoKartMemStore : GoKartStorage {

    private val gokarts = ArrayList<GoKartModel>()

    override fun create(gokart: GoKartModel) {
        gokarts.add(gokart)
        logAll()
    }

    private fun logAll() {
        gokarts.forEach { Timber.i("$it") }
    }

    override fun findAll(): List<GoKartModel> {
        return gokarts
    }
}