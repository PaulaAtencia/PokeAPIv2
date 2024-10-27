package com.example.pokeapiversion2.ui.pokemonList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeapiversion2.R
import com.example.pokeapiversion2.core.Pokemon
import com.example.pokeapiversion2.databinding.PokemonItemBinding

class PokemonListAdapter: ListAdapter<Pokemon, PokemonListAdapter.PokemonListViewHolder>(PokemonDifferentiator)
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder
    {
        val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int)
    {
        val pokemon = getItem(position)
        holder.bind(pokemon)
    }

    object PokemonDifferentiator: DiffUtil.ItemCallback<Pokemon>()
    {

        override fun areItemsTheSame(prev: Pokemon, new: Pokemon): Boolean
        {
            return prev.id == new.id
        }

        override fun areContentsTheSame(prev: Pokemon, new: Pokemon): Boolean
        {
            return prev.id == new.id &&
                prev.name == new.name &&
                prev.type1 == new.type1 &&
                prev.type2 == new.type2
        }
    }

    inner class PokemonListViewHolder(private val binding: PokemonItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(pokemon: Pokemon)
        {
            binding.pokemonId.text = "Nº" + pokemon.id
            binding.pokemonName.text = pokemon.name.substring(0,1).uppercase() + pokemon.name.substring(1)

            // Mostrar tipos
            binding.pokemonType1.text = pokemon.type1
            binding.pokemonType2.text = pokemon.type2 ?: ""
            binding.pokemonType2.visibility = if (pokemon.type2 != null) View.VISIBLE else View.GONE

            // Cargar el sprite del Pokémon
            Glide.with(itemView.context)
                .load(pokemon.sprite)
                .placeholder(R.drawable.pokeball)
                .error(R.drawable.pokeball)
                .into(binding.pokemonSprite)
        }
    }

}
