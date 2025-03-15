package com.plantapp.mobile.ui.diagnosis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.plantapp.mobile.R
import com.plantapp.mobile.databinding.FragmentDiagnosisBinding

class DiagnosisFragment : Fragment() {
    private lateinit var textDiagnosis: TextView

    private var _binding: FragmentDiagnosisBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val diagnosisViewModel =
            ViewModelProvider(this).get(DiagnosisViewModel::class.java)

        _binding = FragmentDiagnosisBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textDiagnosis = root.findViewById<TextView>(R.id.text_diagnosis)
        textDiagnosis.visibility = View.VISIBLE

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}