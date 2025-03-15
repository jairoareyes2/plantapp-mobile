package com.plantapp.mobile.ui.create_spot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plantapp.mobile.models.Spot

class SpotViewModel : ViewModel() {
    private val _spotList = MutableLiveData<MutableList<Spot>>(mutableListOf())
    val spotList: LiveData<MutableList<Spot>> = _spotList

    fun addSpot(spot: Spot) {
        val currentList = _spotList.value ?: mutableListOf()
        currentList.add(spot)
        _spotList.value = currentList
    }
}