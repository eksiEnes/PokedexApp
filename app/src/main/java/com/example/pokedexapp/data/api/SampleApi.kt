package com.example.pokedexapp.data.api

import com.example.pokedexapp.data.model.request.SampleRequestModel
import com.example.pokedexapp.data.model.response.SampleResponseModel
import retrofit2.http.Body
import retrofit2.http.GET

interface SampleApi {

    @GET(ENDPOINT_SAMPLE)
    suspend fun sampleRequest(@Body requestModel: SampleRequestModel): SampleResponseModel


    private companion object{

        const val ENDPOINT_SAMPLE = "sample-endpoint"
    }
}