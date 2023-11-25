package com.example.pokedexapp.data.repository

import com.example.pokedexapp.data.model.response.Pokemon
import com.example.pokedexapp.data.model.uimodel.PokemonItem
import com.example.pokedexapp.utils.Resource

interface PokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<List<PokemonItem>>

    suspend fun getPokemonInfo(name: String) : Resource<Pokemon>
}