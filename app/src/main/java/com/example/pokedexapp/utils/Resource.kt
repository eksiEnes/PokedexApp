package com.example.pokedexapp.utils

import com.example.pokedexapp.data.ServerErrorModel

sealed class Resource<out T> {
    data class Success<out T>(val body: T) : Resource<T>()

    object Loading : Resource<Nothing>()

    data class Error(val error: ServerErrorModel) : Resource<Nothing>()
}
