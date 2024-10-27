package com.example.pokeapiversion2.core.remote

import com.google.gson.annotations.SerializedName

data class PokemonResponse (
    val id: Int,
    val name: String,
    val types: List<TypeResponse>,
    val sprites: SpritesResponse?
)

data class TypeResponse (
    val type: TypeNameResponse
)

data class TypeNameResponse (
    val name: String
)

data class SpritesResponse(
    val front_default: String?
)