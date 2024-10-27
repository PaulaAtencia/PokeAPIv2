package com.example.pokeapiversion2.core.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI
{
    @GET("pokemon/{id}")
    suspend fun fetchPokemon(@Path("id")id: Int): Response<PokemonResponse>

    //@GET("/pokemon/{name}")
    //suspend fun fetchPokemon(@Path("name")name: String): Response<PokemonResponse>
}