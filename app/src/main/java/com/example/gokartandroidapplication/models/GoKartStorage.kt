package com.example.gokartandroidapplication.models

import timber.log.Timber

interface GoKartStorage {
    fun findAll(): List<GoKartModel>
    fun create(gokart: GoKartModel)
     fun update(gokart: GoKartModel)

}

