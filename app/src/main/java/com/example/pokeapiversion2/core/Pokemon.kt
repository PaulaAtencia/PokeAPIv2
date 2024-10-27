package com.example.pokeapiversion2.core

// Clase de datos del pokemon obtenidos de la API.
data class Pokemon (
    val id:Int,
    val name: String,
    val type1: String,
    val type2: String?,
    val sprite: String?
)