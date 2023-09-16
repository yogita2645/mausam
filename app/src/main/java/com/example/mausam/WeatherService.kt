package com.example.mausam

import android.location.Location
import com.example.mausam.response.City
import com.example.mausam.response.GeoData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.openweathermap.org/data/2.5/weather?q={city}&appid={API key}
//https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}

const val BASE_URL = "https://api.openweathermap.org/"
private const val API_KEY = "befa1c274c93237d9a555492646e100a"
//var LAT =
interface WeatherService {

    @GET("data/2.5/weather?appid=$API_KEY")
    fun getCoordinates(@Query("q") city:String): Call<GeoData>

//    @GET("data/2.5/weather?lat={lat}&lon={lon}&appid={API key}")
}
 object GeoData{
     val geoInstance: WeatherService
     init{
         val retrofit = Retrofit.Builder()
             .baseUrl(BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
         geoInstance= retrofit.create(WeatherService::class.java)
     }
 }