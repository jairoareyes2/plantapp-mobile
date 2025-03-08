package com.plantapp.mobile.ui.suggestions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.plantapp.mobile.databinding.FragmentSuggestionsBinding

class SuggestionsFragment : Fragment() {

    private var _binding: FragmentSuggestionsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(SuggestionsViewModel::class.java)

        _binding = FragmentSuggestionsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSuggestions
        profileViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}