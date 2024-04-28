package com.example.gokartandroidapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gokartandroidapplication.databinding.CardGokartBinding
import com.example.gokartandroidapplication.models.GoKartModel
import com.squareup.picasso.Picasso

// Interface for handling click events on GoKart items
interface SearchListener {
    // Function to be implemented to handle click events
    fun onSearchClick(gokart: GoKartModel,  position: Int)
}

// Adapter class for the RecyclerView to display GoKart items
class SearchAdapter(
    // List of GoKartModel items to display
    private var gokarts: List<GoKartModel>,
    // Listener for click events on GoKart items
    private val listener: SearchListener,


    ) : RecyclerView.Adapter<SearchAdapter.MainHolder>() {

    // Create ViewHolder instances
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        // Inflate the layout for each item
        val binding = CardGokartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    // Bind data to ViewHolder
    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val gokart = gokarts[holder.adapterPosition]
        holder.bind(gokart, listener)
    }

    // Get the number of items in the list
    override fun getItemCount(): Int = gokarts.size

    // ViewHolder class to hold references to views
    class MainHolder(private val binding: CardGokartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Bind data to views
        fun bind(gokart: GoKartModel, listener: SearchListener) {
            // Set data to TextViews
            binding.DriverName.text = gokart.name
            binding.CarModel.text = gokart.carModel


            // Set click listener to the root view
            binding.root.setOnClickListener { listener.onSearchClick(gokart,adapterPosition) }
        }
    }
}