package com.plantapp.mobile.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plantapp.mobile.models.Plant

class PlantViewModel : ViewModel() {
    private val _plantList = MutableLiveData<MutableList<Plant>>(mutableListOf())
    val plantList: LiveData<MutableList<Plant>> = _plantList

    fun addPlant(plant: Plant) {
        val currentList = _plantList.value ?: mutableListOf()
        currentList.add(plant)
        _plantList.value = currentList
    }
}