package com.example.gokartandroidapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gokartandroidapplication.databinding.CardGokartBinding
import com.example.gokartandroidapplication.models.GoKartMemStore
import com.example.gokartandroidapplication.models.GoKartModel


interface GoKartListener {
    fun onGoKartClick(gokart: GoKartModel)
}

class GoKartAdapter constructor(private var gokarts: List<GoKartModel>,
                                private val listener: GoKartListener)
                                :
    RecyclerView.Adapter<GoKartAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardGokartBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val gokart = gokarts[holder.adapterPosition]
        holder.bind(gokart, listener)
    }

    override fun getItemCount(): Int = gokarts.size

    class MainHolder(private val binding : CardGokartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(gokart: GoKartModel, listener: GoKartListener) {
            binding.DriverName.text = gokart.name
            binding.CarModel.text = gokart.carModel
            binding.root.setOnClickListener { listener.onGoKartClick(gokart)}
        }
    }
}