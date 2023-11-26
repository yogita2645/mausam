package com.example.mausam

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mausam.databinding.ActivityResultsBinding
import com.example.mausam.response.Forecast
import com.example.mausam.response.ListItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultsActivity : AppCompatActivity(), View.OnClickListener {

    val binding by lazy { ActivityResultsBinding.inflate(layoutInflater) }
    lateinit var forecastAdapter : ForecastAdapter
    lateinit var fitemList: List<ListItem>
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initControl()

        val cityName:String =intent.getStringExtra("City").toString()
            binding.cityName.setText(cityName)
        setParams(cityName)
        getCoordinates(cityName)

    }
    fun setParams(CityName:String){
        binding.cityName.text= CityName
        binding.stateName.text="India"
    }

    private fun moveToHomeActivity() {
        var intent=Intent(this@ResultsActivity, HomeActivity::class.java)
        intent.putExtra("FROM", "ResultsActivity")
        startActivity(intent)
        finish()
    }

    fun getCoordinates(city:String) {
        val forecast=WeatherService.geoInstance.getCoordinates(city=city)
        forecast.enqueue(object : Callback<Forecast> {
            override fun onResponse(call: Call<Forecast>, response: Response<Forecast>) {
                val forecast=response.body()
                if (forecast != null) {
                    Log.d("mausam", forecast.toString())
                    fitemList=forecast.list
                    binding.recyclerForecast.layoutManager = LinearLayoutManager(this@ResultsActivity,
                        LinearLayoutManager.HORIZONTAL,false)
                    forecastAdapter =  ForecastAdapter(fitemList)
                    binding.recyclerForecast.adapter = forecastAdapter
                    binding.currTemp.text=(Math.ceil(fitemList[0].main.temp) -273.00).toString()+ "°C"
                }
            }

            override fun onFailure(call: Call<Forecast>, t: Throwable) {
                Log.d("MyLocationsActivity", "Error in fetching forecast for this city", t)
            }
        })
    }

    private fun initControl() {
        binding.backBtn.setOnClickListener(this@ResultsActivity)
    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.backBtn -> {
                moveToHomeActivity()
            }
        }
    }
}