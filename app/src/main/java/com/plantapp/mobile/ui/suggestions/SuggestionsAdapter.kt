package com.plantapp.mobile.ui.suggestions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.plantapp.mobile.R
import com.plantapp.mobile.models.Suggestion


class SuggestionsAdapter(private val suggestionsList: List<Suggestion>) : RecyclerView.Adapter<SuggestionsAdapter.SuggestionViewHolder>() {

    class SuggestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val initialSuggestion: TextView = itemView.findViewById(R.id.initialSuggestion)
        val suggestionNameTextView: TextView = itemView.findViewById(R.id.suggestionNameTextView)
        val suggestionDescriptionTextView: TextView = itemView.findViewById(R.id.suggestionDescriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_suggestion, parent, false)
        return SuggestionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SuggestionViewHolder, position: Int) {
        val currentSuggestion = suggestionsList[position]
        holder.initialSuggestion.text = currentSuggestion.initial
        holder.suggestionNameTextView.text = currentSuggestion.title
        holder.suggestionDescriptionTextView.text = currentSuggestion.description
    }

    override fun getItemCount(): Int {
        return suggestionsList.size
    }
}