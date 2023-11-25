package com.example.pokedexapp.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<out T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: @UnsafeVariance T)
}