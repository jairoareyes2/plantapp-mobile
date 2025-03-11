package com.plantapp.mobile.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.plantapp.mobile.R
import com.plantapp.mobile.databinding.FragmentHomeBinding
import com.plantapp.mobile.ui.PlantViewModel

class HomeFragment : Fragment() {

    private val plantViewModel: PlantViewModel by activityViewModels()
    private lateinit var plantAdapter: PlantAdapter
    private lateinit var emptyPlantListTextView: TextView
    private lateinit var plantListRecyclerView: RecyclerView

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.createPlantBtn.setOnClickListener {
            homeViewModel.onCreatePlantClicked()
        }

        homeViewModel.navigateToCreatePlant.observe(viewLifecycleOwner, Observer { shouldNavigate ->
            if (shouldNavigate) {
                findNavController().navigate(R.id.action_navigation_home_to_create_plant)
                homeViewModel.onNavigatedToCreatePlant()
            }
        })

        emptyPlantListTextView = root.findViewById(R.id.text_empty_plant_list)
        plantListRecyclerView = root.findViewById(R.id.plantListRecyclerView)
        plantListRecyclerView.layoutManager = LinearLayoutManager(context)

        plantAdapter = PlantAdapter(emptyList())
        plantListRecyclerView.adapter = plantAdapter

        plantViewModel.plantList.observe(viewLifecycleOwner) { plantList ->
            plantAdapter = PlantAdapter(plantList)
            plantListRecyclerView.adapter = plantAdapter
            if (plantList.isEmpty()) {
                emptyPlantListTextView.visibility = View.VISIBLE
                plantListRecyclerView.visibility = View.GONE
            } else {
                emptyPlantListTextView.visibility = View.GONE
                plantListRecyclerView.visibility = View.VISIBLE
            }
        }
            return root
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}