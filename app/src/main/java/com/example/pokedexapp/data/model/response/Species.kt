package com.example.pokedexapp.data.model.response


import com.google.gson.annotations.SerializedName

data class Species(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)