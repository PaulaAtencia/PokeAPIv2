package com.example.pokeapiversion2.core.remote

import com.example.pokeapiversion2.core.Pokemon
import com.example.pokeapiversion2.core.services.MapDataService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(private val api: PokemonAPI)
{
    private var mutablePokemonList = MutableStateFlow<List<Pokemon>>(listOf())
    val pokemonList: StateFlow<List<Pokemon>> get() = mutablePokemonList.asStateFlow()

    suspend fun fetchPokemon(id: Int): Pokemon {
        val response = api.fetchPokemon(id)
        if(response.isSuccessful)
        {
            val pokemon = response.body()!!
            return MapDataService.mapPokemon(pokemon)
        }
        throw Exception("Response no obtenida")
    }

    /*
    suspend fun fetchPokemon(name: String): Pokemon {
        val response = api.fetchPokemon(name)
        if(response.isSuccessful)
        {
            val pokemon = response.body()!!
            return MapDataService.mapPokemonModel(pokemon)
        }
        throw Exception("Response no obtenida")
    }
    */

    suspend fun fetchList(idLimit: Int): List<Pokemon> {
        val pokemonList = mutableListOf<Pokemon>()
        for (i in 1..idLimit) {
            pokemonList.add(fetchPokemon(i))
        }
        mutablePokemonList.value = pokemonList
        return pokemonList
    }
}