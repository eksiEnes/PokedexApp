package com.example.pokedexapp.ui.features.pokedex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.data.model.uimodel.PokemonItem
import com.example.pokedexapp.data.repository.PokemonRepository
import com.example.pokedexapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _pokedexLiveData = MutableLiveData<Resource<List<PokemonItem>>>()
    val pokedexLiveData: LiveData<Resource<List<PokemonItem>>> = _pokedexLiveData

    val pokedexSortTypeLiveData = MutableLiveData<String>()

    init {
        getPokedex()
    }


    fun getPokedex(){
        viewModelScope.launch {
            _pokedexLiveData.postValue(Resource.Loading)
            //Random page and limit is 20
            val result = pokemonRepository.getPokemonList(20, 1)
            _pokedexLiveData.postValue(result)
        }
    }
}