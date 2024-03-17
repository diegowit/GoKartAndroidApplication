package com.example.gokartandroidapplication.models
import com.example.gokartandroidapplication.models.TournamentModel
import timber.log.Timber

class TournamentMemStore : TournamentStorage {

    val tournaments = ArrayList<TournamentModel>()

    var lastId = 0L

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
            title = tournament.title
            description = tournament.description
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
}