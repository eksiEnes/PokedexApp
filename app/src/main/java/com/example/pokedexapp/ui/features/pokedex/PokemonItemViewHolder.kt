package com.example.pokedexapp.ui.features.pokedex

import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.data.model.uimodel.PokemonItem
import com.example.pokedexapp.databinding.ItemPokedexCardBinding
import com.example.pokedexapp.ext.idWithTag
import com.squareup.picasso.Picasso

class PokemonItemViewHolder(
    private val binding: ItemPokedexCardBinding
): RecyclerView.ViewHolder(binding.root) {
     fun bind(item: PokemonItem) {
        with(binding){
            textViewId.text = item.id.toString().idWithTag(3)
            Picasso.get().load(item.imageUrl).into(imageViewPokemon)
            textViewName.text = item.name.replaceFirstChar {
                it.uppercaseChar()
            }
        }
    }

}