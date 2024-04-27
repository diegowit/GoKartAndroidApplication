package com.example.gokartandroidapplication.models

interface TournamentStorage {
    fun findAll(): List<TournamentModel>
    fun create(tournament: TournamentModel)
    fun update(tournament: TournamentModel)


}