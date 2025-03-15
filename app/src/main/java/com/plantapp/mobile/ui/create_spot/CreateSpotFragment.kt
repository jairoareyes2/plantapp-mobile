package com.plantapp.mobile.ui.create_spot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.plantapp.mobile.R
import com.plantapp.mobile.databinding.FragmentCreateSpotBinding
import com.plantapp.mobile.models.Spot

class CreateSpotFragment : Fragment() {
    private var _binding: FragmentCreateSpotBinding? = null
    private val binding get() = _binding!!

    private val spotViewModel: SpotViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateSpotBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val createSpotButton: Button = root.findViewById(R.id.createSpotBtn)
        val cancelSpotButton: Button = root.findViewById(R.id.cancelarSpotBtn)
        val spotNameEditText: EditText = root.findViewById(R.id.spotNameInput)
        val spotCapacityEditText: EditText = root.findViewById(R.id.spotCapacityInput)

        createSpotButton.setOnClickListener {
            val spotName = spotNameEditText.text.toString()
            val spotCapacity = spotCapacityEditText.text.toString()

            if (spotName.isEmpty()) {
                Toast.makeText(context, "Por favor ingrese un nombre para el espacio", Toast.LENGTH_SHORT).show()
            } else {
                if (spotCapacity.isEmpty()) {
                    Toast.makeText(context, "Por favor ingresa la capacidad del espacio", Toast.LENGTH_SHORT).show()
                } else {
                    val newSpot = Spot(spotName, spotCapacity)
                    spotViewModel.addSpot(newSpot)
                    findNavController().navigate(R.id.action_create_spot_to_create_plant)
                }
            }
        }

        cancelSpotButton.setOnClickListener {
            findNavController().navigateUp()
        }
        return root
    }
}