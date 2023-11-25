package com.example.pokedexapp.ui.features.pokedex

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.data.model.uimodel.PokemonItem
import com.example.pokedexapp.databinding.ItemPokedexCardBinding

class PokemonListAdapter: RecyclerView.Adapter<PokemonItemViewHolder>() {

    private var itemList = listOf<PokemonItem>()
    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(itemList: List<PokemonItem>){
        this.itemList = itemList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonItemViewHolder {
        val binding = ItemPokedexCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonItemViewHolder(binding)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: PokemonItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }
}