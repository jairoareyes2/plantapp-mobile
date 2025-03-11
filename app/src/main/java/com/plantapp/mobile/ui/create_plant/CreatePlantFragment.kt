package com.plantapp.mobile.ui.create_plant

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.plantapp.mobile.R
import com.plantapp.mobile.databinding.FragmentCreatePlantBinding
import com.plantapp.mobile.models.Plant
import com.plantapp.mobile.ui.PlantViewModel


class CreatePlantFragment : Fragment() {

    private var _binding: FragmentCreatePlantBinding? = null

    private val binding get() = _binding!!

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

        binding.openTimePickerBtn.setOnClickListener {
            showTimePicker()
        }

        val spinner: Spinner = binding.spacesSelector

        val spinnerItems = listOf("Jardín", "Patio", "Agregar espacio +")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerItems)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(requireContext(), "Selected: $selectedItem", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        val createPlantButton: Button = root.findViewById(R.id.createPlantBtn)
        val plantNameEditText: EditText = root.findViewById(R.id.plantNameInput)

        createPlantButton.setOnClickListener {
            val plantName = plantNameEditText.text.toString()
            if (plantName.isEmpty()) {
                Toast.makeText(context, "Please enter a plant name", Toast.LENGTH_SHORT).show()
            } else {
                val newPlant = Plant(plantName, "Space", "10:00 AM", "Mon, Wed, Fri")
                plantViewModel.addPlant(newPlant)
                findNavController().navigateUp()
            }
        }

        return root
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(hour)
            .setMinute(minute)
            .setTitleText("Seleccione la hora de riego")
            .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
            .setNegativeButtonText("Cancelar")
            .setPositiveButtonText("Aceptar")
            .build()

        picker.addOnPositiveButtonClickListener {
            val pickedHour = picker.hour
            val pickedMinute = picker.minute
            Toast.makeText(requireContext(), "Time selected: $pickedHour:$pickedMinute", Toast.LENGTH_SHORT).show()
        }

        picker.show(childFragmentManager, "tag")

        val checkBoxes = listOf(
            binding.mondayCheckBox,
            binding.tuesdayCheckBox,
            binding.wednesdayCheckBox,
            binding.thursdayCheckBox,
            binding.fridayCheckBox,
            binding.saturdayCheckBox,
            binding.sundayCheckBox
        )

        checkBoxes.forEach { checkBox ->
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                val day = when (checkBox.id) {
                    R.id.mondayCheckBox -> "Lunes"
                    R.id.tuesdayCheckBox -> "Martes"
                    R.id.wednesdayCheckBox -> "Miércoles"
                    R.id.thursdayCheckBox -> "Jueves"
                    R.id.fridayCheckBox -> "Viernes"
                    R.id.saturdayCheckBox -> "Sábado"
                    R.id.sundayCheckBox -> "Domingo"
                    else -> "Desconocido"
                }
                val message = if (isChecked) "$day seleccionado" else "$day deseleccionado"
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}