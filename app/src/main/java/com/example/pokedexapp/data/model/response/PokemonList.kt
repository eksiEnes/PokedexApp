package com.example.pokedexapp.data.model.response


import com.example.pokedexapp.data.model.uimodel.PokemonItem
import com.google.gson.annotations.SerializedName

data class PokemonList(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)

fun PokemonList.toPokemonItemList(): List<PokemonItem> {
    val regex = Regex("""/(\d+)/$""")

    return this.results.map {
        val matchResult = regex.find(it.url)
        val extractedNumber = matchResult?.groupValues?.get(1)
        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${extractedNumber}.png"
        PokemonItem(extractedNumber!!.toInt(), url, it.name)
    }
}