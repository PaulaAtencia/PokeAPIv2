package com.example.pokeapiversion2.ui.pokemonList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.pokeapiversion2.databinding.FragmentPokemonListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListFragment : Fragment()
{
    private lateinit var binding: FragmentPokemonListBinding
    private val viewModel: PokemonListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            val recyclerView = binding.pokemonList
            recyclerView.adapter = PokemonListAdapter()

            viewModel.pokemonListUI.collect { item ->
                when (item) {
                    PokemonListUI.Loading -> {}
                    is PokemonListUI.Success -> {
                        (recyclerView.adapter as PokemonListAdapter).submitList(item.pokemonList)
                    }
                }
            }
        }
    }
}
