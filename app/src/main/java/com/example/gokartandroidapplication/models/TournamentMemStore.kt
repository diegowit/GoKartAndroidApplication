package com.example.gokartandroidapplication.models

import timber.log.Timber

object TournamentMemStore : TournamentStorage {

    private val tournaments = ArrayList<TournamentModel>()

    private var lastId = 0L

    private fun getId(): Long {
        lastId++
        return lastId
    }

    override fun create(tournament: TournamentModel) {
        tournament.id = getId()
        tournaments.add(tournament)
        logAll()
    }

    override fun update(tournament: TournamentModel) {
        val foundTournament = tournaments.find { it.id == tournament.id }
        foundTournament?.apply {
            name = tournament.name
            date = tournament.date
            location = tournament.location
            laps = tournament.laps
        }
        logAll()
    }

    private fun logAll() {
        tournaments.forEach { Timber.i("$it") }
    }

    override fun findAll(): List<TournamentModel> {
        return tournaments
    }