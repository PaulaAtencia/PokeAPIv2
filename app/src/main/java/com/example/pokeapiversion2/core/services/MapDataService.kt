package com.example.pokeapiversion2.core.services

import com.example.pokeapiversion2.core.remote.PokemonResponse
import com.example.pokeapiversion2.core.Pokemon

class MapDataService
{
    companion object
    {
        fun mapPokemon(response:PokemonResponse): Pokemon
        {
            return Pokemon(
                response.id, response.name, response.types[0].type.name,
                response.types.getOrNull(1)?.type?.name,
                response.sprites?.front_default ?: ""
            )
        }
    }
}