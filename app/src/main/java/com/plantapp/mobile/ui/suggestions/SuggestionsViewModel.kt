package com.plantapp.mobile.ui.suggestions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plantapp.mobile.models.Suggestion

class SuggestionsViewModel : ViewModel() {

    private val _list = MutableLiveData<List<Suggestion>>().apply {
        value = listOf(Suggestion("R", "Riego", "Se debe regar la planta"),Suggestion("R", "Riego", "Se debe regar la planta"), Suggestion("R", "Riego", "Se debe regar la planta"), Suggestion("R", "Riego", "Se debe regar la planta"))
    }
    val list: LiveData<List<Suggestion>> = _list
}