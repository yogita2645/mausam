package com.example.mausam.response

data class WeatherForecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ListItem>,
    val message: Int
)


data class ListItem(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain,
    val systum : Systum,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

data class Systum (
    val pod: String
)


data class Rain(
    val `3h`: Double
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