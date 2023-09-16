package com.example.mausam

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mausam.databinding.ActivityMyLocationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private fun <T> Call<T>.enqueue(callback: Callback<GeoData>) {

}

class MyLocationsActivity: AppCompatActivity() {

    val binding by lazy { ActivityMyLocationBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getCoordinates()
    }

    private fun getCoordinates() {
        val geoData= GeoData.geoInstance.getCoordinates(binding.etCity.text.toString())
        geoData.enqueue(object: Callback<GeoData>{
            override fun onResponse(call: Call<GeoData>, response: Response<GeoData>) {
                Log.d("MyLocationsActivity","Error in fetching geoData for this city")
            }

            override fun onFailure(call: Call<GeoData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}