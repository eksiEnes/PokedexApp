package com.example.pokedexapp.ui.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.core.BaseViewModel
import com.example.pokedexapp.data.model.response.Pokemon
import com.example.pokedexapp.data.repository.PokemonRepository
import com.example.pokedexapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) :
    BaseViewModel() {

    private val _pokemonLiveData = MutableLiveData<Resource<Pokemon>>()
    val pokemonLiveData: LiveData<Resource<Pokemon>> = _pokemonLiveData

    fun getPokemonInfo(id: Int) {
        viewModelScope.launch {
            _pokemonLiveData.postValue(Resource.Loading)
            val result = pokemonRepository.getPokemonInfo(id.toString())
            _pokemonLiveData.postValue(result)
        }
    }
}