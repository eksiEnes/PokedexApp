package com.example.pokedexapp.data.repository

import com.example.pokedexapp.data.ServerErrorModel
import com.example.pokedexapp.data.api.PokeApi
import com.example.pokedexapp.data.model.response.Pokemon
import com.example.pokedexapp.data.model.response.PokemonList
import com.example.pokedexapp.utils.Resource
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(val api: PokeApi) : PokemonRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try{
            api.getPokemonList(limit , offset)
        }catch (e: Exception){
            return Resource.Error(ServerErrorModel("An unknown error occured"))
        }
        return Resource.Success(response)
    }

    override suspend fun getPokemonInfo(name: String): Resource<Pokemon> {
        val response = try{
            api.getPokemonInfo(name)
        }catch (e: Exception){
            return Resource.Error(ServerErrorModel("An unknown error occured"))
        }
        return Resource.Success(response)
    }


}