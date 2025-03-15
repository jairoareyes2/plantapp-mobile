package com.plantapp.mobile.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.plantapp.mobile.R
import com.plantapp.mobile.models.Plant


class PlantAdapter(private val plantList: List<Plant>) : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val plantNameTextView: TextView = itemView.findViewById(R.id.plantNameTextView)
        val plantSpaceTextView: TextView = itemView.findViewById(R.id.plantSpaceTextView)
        val wateringTimeTextView: TextView = itemView.findViewById(R.id.wateringTimeTextView)
        val wateringDaysTextView: TextView = itemView.findViewById(R.id.wateringDaysTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false)
        return PlantViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val currentPlant = plantList[position]
        holder.plantNameTextView.text = currentPlant.name
        holder.plantSpaceTextView.text = currentPlant.space
        holder.wateringTimeTextView.text = currentPlant.wateringTime
        holder.wateringDaysTextView.text = currentPlant.wateringDays
    }

    override fun getItemCount(): Int {
        return plantList.size
    }
}