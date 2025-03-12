package com.plantapp.mobile.ui.create_spot

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.plantapp.mobile.R

class CreateSpotFragment : Fragment() {

    companion object {
        fun newInstance() = CreateSpotFragment()
    }

    private val viewModel: CreateSpotViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_create_spot, container, false)
    }
}