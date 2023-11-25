package com.example.pokedexapp.core

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH: BaseViewHolder<T>>(): RecyclerView.Adapter<VH>() {

    private val items = mutableListOf<T>()

    override fun getItemCount() = items.size

    abstract fun onCreateHolder(parent: ViewGroup, viewType: Int): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return onCreateHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<T>){
        with(this.items){
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    fun getItemPosition(position: Int) = items.getOrNull(position)
}