package com.example.mausam.response

data class Forecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ListItem>,
    val message: Int
)

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)

data class ListItem(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    var main: Main,
    val pop: Double,
    val sys: Sys,
    val visibility: Int,
    var weather: List<Weather>,
    val wind: Wind
)