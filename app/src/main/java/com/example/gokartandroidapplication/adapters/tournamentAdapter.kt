package com.example.gokartandroidapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gokartandroidapplication.databinding.CardTournamentBinding
import com.example.gokartandroidapplication.models.TournamentModel

interface TournamentListener {
    fun onTournamentClick(tournament: TournamentModel)
}

class TournamentAdapter(
    private var tournaments: List<TournamentModel>,
    private val listener: TournamentListener
) :
    RecyclerView.Adapter<TournamentAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardTournamentBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val tournament = tournaments[holder.adapterPosition]
        holder.bind(tournament, listener)
    }

    override fun getItemCount(): Int = tournaments.size

    class MainHolder(private val binding: CardTournamentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tournament: TournamentModel, listener: TournamentListener) {
            binding.tournamentTitle.text = tournament.title
            binding.tournamentDescription.text = tournament.description
            binding.root.setOnClickListener { listener.onTournamentClick(tournament) }
        }
    }
}