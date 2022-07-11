package com.example.android.sports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.android.sports.databinding.FragmentSportsListBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class SportsListFragment : Fragment() {

    private val sportsViewModel: SportsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSportsListBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSportsListBinding.bind(view)

        // Initialize the adapter and set it to the RecyclerView.
        val adapter = SportsAdapter(
            ShortListener { sport ->
                // Update the user selected sport as the current sport in the shared viewmodel
                // This will automatically update the dual pane content
                sportsViewModel.updateCurrentSport(sport)
                // Navigate to the details screen
                val action = SportsListFragmentDirections.actionSportsListFragmentToNewsFragment()
                this.findNavController().navigate(action)
            },
            LongListener {
                Toast.makeText(
                    requireContext(),
                    "${it.titleResourceId}",
                    Toast.LENGTH_LONG
                ).show()
                true
            }
        )

        binding.recyclerView.adapter = adapter
        adapter.submitList(sportsViewModel.sportsData)
    }
}
