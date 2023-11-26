package com.example.mausam.response

data class Weather(
    var description: String="",
    val icon: String,
    val id: Int,
    val main: String
)