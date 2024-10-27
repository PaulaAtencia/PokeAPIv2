package com.example.pokeapiversion2.di

import com.example.pokeapiversion2.core.remote.PokemonAPI
import com.example.pokeapiversion2.core.remote.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule
{
    @Singleton
    @Provides
    fun providePokemonAPI(): PokemonAPI
    {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/") // URL base de la API
            .addConverterFactory(GsonConverterFactory.create()) // Convertidor para JSON
            .build().create(PokemonAPI::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule
{
    @Singleton
    @Provides
    fun providePokemonRepository(api: PokemonAPI): PokemonRepository
    {
        return PokemonRepository(api)
    }
}
