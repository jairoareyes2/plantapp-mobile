package com.plantapp.mobile.ui.suggestions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.plantapp.mobile.databinding.FragmentSuggestionsBinding

class SuggestionsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentSuggestionsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val suggestionViewModel =
            ViewModelProvider(this).get(SuggestionsViewModel::class.java)

        _binding = FragmentSuggestionsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.suggestionsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        suggestionViewModel.list.observe(viewLifecycleOwner) {
            val suggestionsAdapter = SuggestionsAdapter(it)
            recyclerView.adapter = suggestionsAdapter
            recyclerView.visibility = root.visibility
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}