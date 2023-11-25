package com.example.pokedexapp.ui.features.pokedex

import com.example.pokedexapp.core.BaseViewHolder
import com.example.pokedexapp.data.model.uimodel.PokemonItem
import com.example.pokedexapp.databinding.ItemPokedexCardBinding
import com.squareup.picasso.Picasso

class PokemonItemViewHolder(
    private val binding: ItemPokedexCardBinding
) : BaseViewHolder<PokemonItem>(binding.root) {
    override fun bind(item: PokemonItem) {
        with(binding){
            textViewId.text = item.id.toString()
            Picasso.get().load(item.imageUrl).into(imageViewPokemon)
            textViewName.text = item.name.toString()
        }
    }

}