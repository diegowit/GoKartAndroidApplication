package com.example.gokartandroidapplication.models

import timber.log.Timber

interface GoKartStorage {
    fun findAll(): List<GoKartModel>
    fun create(gokart: GoKartModel)
    fun findById(id:Long) : GoKartModel?
     fun update(gokart: GoKartModel)

    fun delete(gokart: GoKartModel)

    fun findByName(name:String) : GoKartModel?
}

