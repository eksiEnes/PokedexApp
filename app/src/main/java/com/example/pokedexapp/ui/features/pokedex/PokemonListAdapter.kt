package com.example.pokedexapp.ui.features.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.pokedexapp.core.BaseAdapter
import com.example.pokedexapp.core.BaseViewHolder
import com.example.pokedexapp.data.model.uimodel.PokemonItem
import com.example.pokedexapp.databinding.ItemPokedexCardBinding

class PokemonListAdapter: BaseAdapter<PokemonItem, BaseViewHolder<PokemonItem>>() {
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<PokemonItem> {
        return PokemonItemViewHolder(ItemPokedexCardBinding.inflate(LayoutInflater.from(parent.context)))
    }
}