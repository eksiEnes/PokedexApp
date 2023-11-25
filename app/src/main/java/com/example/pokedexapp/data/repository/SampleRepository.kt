package com.example.pokedexapp.data.repository

import com.example.pokedexapp.data.NetworkManager
import com.example.pokedexapp.data.NetworkResult
import com.example.pokedexapp.data.api.SampleApi
import com.example.pokedexapp.data.model.response.SampleResponseModel
import kotlinx.coroutines.delay

class SampleRepository(
    private val apiService: SampleApi,
    private val networkManager: NetworkManager
) {

    suspend fun sampleRequest(): NetworkResult<SampleResponseModel> {
        delay(3000L)
//        return networkManager.makeRequest {
//            apiService.sampleRequest(SampleRequestModel(sample = "21"))
//        }
        return NetworkResult.Success(SampleResponseModel(sampleId = 2131))
    }
}