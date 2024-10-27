package com.example.pokeapiversion2.ui.pokemonList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapiversion2.core.remote.PokemonRepository
import com.example.pokeapiversion2.core.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

sealed class PokemonListUI()
{
    data object Loading: PokemonListUI()

    class Success(val pokemonList: List<Pokemon>): PokemonListUI()

    //class Error(val message: String): PokemonListUI()
}

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel()
{
    private val mutablePokemonListUI = MutableStateFlow<PokemonListUI>(PokemonListUI.Loading)
    val pokemonListUI: StateFlow<PokemonListUI> get() = mutablePokemonListUI.asStateFlow()

    init
    {
        viewModelScope.launch {
            withContext(Dispatchers.Main)
            {
                repository.pokemonList.collect { list ->
                    if (list.isEmpty()) mutablePokemonListUI.value = PokemonListUI.Loading
                    else mutablePokemonListUI.value = PokemonListUI.Success(list)
                }
            }
        }

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try
                {
                    repository.fetchList(151)
                }
                catch(e: Exception)
                {
                    Log.e("PokemonListViewModel", "Error al obtener lista de pokémon", e)
                }
            }
        }
    }
}
