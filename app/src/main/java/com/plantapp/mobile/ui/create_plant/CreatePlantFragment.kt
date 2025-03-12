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
import com.plantapp.mobile.R
import com.plantapp.mobile.databinding.FragmentCreatePlantBinding
import com.plantapp.mobile.models.Plant
import com.plantapp.mobile.ui.PlantViewModel


class CreatePlantFragment : Fragment() {

    private var _binding: FragmentCreatePlantBinding? = null

    private val binding get() = _binding!!

    private lateinit var timeSelected: String

    private lateinit var spaceSelected: String

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

        val spaces = listOf("Espacio por defecto", "Jardín", "Patio", "Agregar espacio +")
        val adapter = ArrayAdapter(requireContext(), R.layout.space_item, spaces)
        (binding.spacesDropdownLayout.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        spaceSelected = spaces[0]

        (binding.spacesDropdownLayout.editText as? AutoCompleteTextView)?.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                spaceSelected = parent.getItemAtPosition(position).toString()
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

        createPlantButton.setOnClickListener {
            val plantName = plantNameEditText.text.toString()
            if (plantName.isEmpty()) {
                Toast.makeText(context, "Please enter a plant name", Toast.LENGTH_SHORT).show()
            } else {
                val daysString = selectedDays.joinToString(", ")
                val newPlant = Plant(plantName, spaceSelected, timeSelected, daysString)
                plantViewModel.addPlant(newPlant)
                findNavController().navigateUp()
            }
        }

//        val btnHour = findViewById<MaterialButton>(R.id.btn_hour)
//        val btnAm = findViewById<MaterialButton>(R.id.btn_am)
//        val btnPm = findViewById<MaterialButton>(R.id.btn_pm)
//
//        var selectedHour = 12
//        var selectedAmPm = "AM"
//
//        // Cambiar hora al tocar
//        btnHour.setOnClickListener {
//            val numberPicker = NumberPicker(this).apply {
//                minValue = 1
//                maxValue = 12
//                value = selectedHour
//                setOnValueChangedListener { _, _, newVal ->
//                    selectedHour = newVal
//                    btnHour.text = newVal.toString()
//                }
//            }
//
//            AlertDialog.Builder(this)
//                .setTitle("Selecciona la hora")
//                .setView(numberPicker)
//                .setPositiveButton("OK") { _, _ -> }
//                .show()
//        }
//
//        // Selección de AM/PM
//        btnAm.setOnClickListener {
//            selectedAmPm = "AM"
//            btnAm.isChecked = true
//            btnPm.isChecked = false
//        }
//
//        btnPm.setOnClickListener {
//            selectedAmPm = "PM"
//            btnPm.isChecked = true
//            btnAm.isChecked = false
//        }

//        val hourPicker = findViewById<MaterialButton>(R.id.btn_hour)



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}