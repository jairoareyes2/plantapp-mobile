package com.plantapp.mobile.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _navigateToCreatePlant = MutableLiveData<Boolean>()
    val navigateToCreatePlant: LiveData<Boolean> = _navigateToCreatePlant

    fun onCreatePlantClicked() {
        _navigateToCreatePlant.value = true
    }

    fun onNavigatedToCreatePlant() {
        _navigateToCreatePlant.value = false
    }
}