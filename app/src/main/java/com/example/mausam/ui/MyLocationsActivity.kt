package com.example.mausam.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mausam.databinding.ActivityMyLocationBinding
import com.example.mausam.WeatherService
import com.example.mausam.MainActivity
import com.example.mausam.R
import com.example.mausam.response.GeoData
import com.example.mausam.response.WeatherForecast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyLocationsActivity : AppCompatActivity(), View.OnClickListener {

    val binding by lazy { ActivityMyLocationBinding.inflate(layoutInflater) }
    var city=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initControl()
        getCity()
        getCoordinates()
    }

    fun getCity(){
    city = binding.etCity.toString()
    }

    private fun moveToMainActivity() {
        val intent=Intent(this@MyLocationsActivity, MainActivity::class.java)
        intent.putExtra("FROM", "MyLocationsActivity")
        startActivity(intent)
        finish()
    }

    private fun getCoordinates() {
        val forecast=WeatherService.geoInstance.getCoordinates(city=city)
        forecast.enqueue(object : Callback<WeatherForecast> {
            override fun onResponse(call: Call<WeatherForecast>, response: Response<WeatherForecast>) {
                val forecast=response.body()
                if (forecast != null) {
                    Log.d("mausam", forecast.toString())
                }
            }

            override fun onFailure(call: Call<WeatherForecast>, t: Throwable) {
                Log.d("MyLocationsActivity", "Error in fetching forecast for this city", t)
            }
        })
    }

    private fun initControl() {
        binding.searchBtn.setOnClickListener(this@MyLocationsActivity)
    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.search_btn -> {
                moveToMainActivity()
            }
        }
    }
}