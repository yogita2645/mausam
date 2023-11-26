package com.example.mausam.response

data class Main(
    val feels_like: Double,
    val grnd_level: Int,
    var humidity: Int=0,
    var pressure: Int=0,
    val sea_level: Int,
    var temp: Double=0.0,
    val temp_kf: Double=0.0,
    var temp_max: Double=0.0,
    var temp_min: Double=0.0
)