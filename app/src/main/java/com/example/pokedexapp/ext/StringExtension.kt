package com.example.pokedexapp.ext

fun String.idWithTag(totalDigits: Int): String{
    val number = this.toIntOrNull() ?: return this // Return original string if it's not a valid number
    return String.format("#%0${totalDigits}d", number)
}