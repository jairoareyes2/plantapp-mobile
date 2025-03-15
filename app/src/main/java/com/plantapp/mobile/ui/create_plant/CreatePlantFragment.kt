package com.plantapp.mobile.ui.create_plant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButtonToggleGroup
import com.plantapp.mobile.R
import com.plantapp.mobile.databinding.FragmentCreatePlantBinding
import com.plantapp.mobile.models.Plant
import com.plantapp.mobile.ui.PlantViewModel
import java.util.Locale


class CreatePlantFragment : Fragment() {

    private var _binding: FragmentCreatePlantBinding? = null

    private val binding get() = _binding!!

    private lateinit var timeSelected: String

    private lateinit var spotSelected: String

    private val selectedDays = mutableListOf<String>()

    private val plantViewModel: PlantViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val createPlantViewModel =
            ViewModelProvider(this).get(CreatePlantViewModel::class.java)

        _binding = FragmentCreatePlantBinding.inflate(inflater, container, false)
        val root: View = binding.root

        timeSelected = "00:00 AM"

        val spots = listOf("Espacio por defecto", "Jardín", "Patio", "Agregar espacio +")
        val adapter = ArrayAdapter(requireContext(), R.layout.spot_item, spots)
        (binding.spotsDropdownLayout.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        spotSelected = spots[0]

        (binding.spotsDropdownLayout.editText as? AutoCompleteTextView)?.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                spotSelected = parent.getItemAtPosition(position).toString()
                if (position == spots.size - 1) {
                    findNavController().navigate(R.id.action_create_plant_to_create_spot)
                }
            }

        val daysCheckBoxes = listOf(
            binding.mondayCheckBox,
            binding.tuesdayCheckBox,
            binding.wednesdayCheckBox,
            binding.thursdayCheckBox,
            binding.fridayCheckBox,
            binding.saturdayCheckBox,
            binding.sundayCheckBox
        )

        daysCheckBoxes.forEach { checkBox ->
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                val day = when (checkBox.id) {
                    R.id.mondayCheckBox -> "L"
                    R.id.tuesdayCheckBox -> "M"
                    R.id.wednesdayCheckBox -> "M"
                    R.id.thursdayCheckBox -> "J"
                    R.id.fridayCheckBox -> "V"
                    R.id.saturdayCheckBox -> "S"
                    R.id.sundayCheckBox -> "D"
                    else -> "-"
                }
                if (isChecked) {
                    selectedDays.add(day)
                } else {
                    selectedDays.remove(day)
                }
            }
        }

        val createPlantButton: Button = root.findViewById(R.id.createPlantBtn)
        val plantNameEditText: EditText = root.findViewById(R.id.plantNameInput)

        val hourInput: EditText = root.findViewById(R.id.hour_input)
        val minuteInput: EditText = root.findViewById(R.id.minute_input)
        val toggleGroup = root.findViewById<MaterialButtonToggleGroup>(R.id.am_pm_toggle)

        createPlantButton.setOnClickListener {
            val plantName = plantNameEditText.text.toString()

            val hour = hourInput.text.toString().toIntOrNull()
            val minute = minuteInput.text.toString().toIntOrNull()
            val selectedButtonId = toggleGroup.checkedButtonId

            if (plantName.isEmpty()) {
                Toast.makeText(context, "Por favor ingrese un nombre para la planta", Toast.LENGTH_SHORT).show()
            } else {
                if (hour != null && minute != null) {
                    if (hour !in 0..12 || minute !in 0..59) {
                        Toast.makeText(context, "Por favor ingrese una hora válida", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    timeSelected = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
                }

                if (selectedButtonId == R.id.button_am) {
                    timeSelected += " AM"
                } else if (selectedButtonId == R.id.button_pm) {
                    timeSelected += " PM"
                }

                if (selectedDays.isEmpty()) {
                    Toast.makeText(context, "Por favor seleccione al menos un día de la semana", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val daysString = selectedDays.joinToString(", ")

                val newPlant = Plant(plantName, spotSelected, timeSelected, daysString)
                plantViewModel.addPlant(newPlant)
                findNavController().navigateUp()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}