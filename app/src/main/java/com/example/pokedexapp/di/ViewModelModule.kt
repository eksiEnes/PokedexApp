package com.example.pokedexapp.di

import com.example.pokedexapp.data.NetworkManager
import com.example.pokedexapp.data.api.PokeApi
import com.example.pokedexapp.data.api.SampleApi
import com.example.pokedexapp.data.repository.PokemonRepository
import com.example.pokedexapp.data.repository.PokemonRepositoryImpl
import com.example.pokedexapp.data.repository.SampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {


    @Provides
    @ViewModelScoped
    fun providesSampleRepository(
        apiService: SampleApi,
        networkManager: NetworkManager
    ): SampleRepository =
        SampleRepository(apiService, networkManager)

    @Provides
    @ViewModelScoped
    fun providesPokemonRepository(
        apiService: PokeApi,
    ): PokemonRepository =
        PokemonRepositoryImpl(apiService)
}