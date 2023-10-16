package com.example.mausam

import android.content.Intent
import android.hardware.biometrics.BiometricManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mausam.databinding.ActivityMainBinding
import com.example.mausam.response.ListItem
import com.example.mausam.response.WeatherForecast
import com.example.mausam.ui.MyLocationsActivity
import com.example.mausam.ui.SharedPreferenceUtil
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var forcastAdapter : ForecastAdapter
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var ItemList:List<ListItem>
    private lateinit var requiredItemList:List<String>
    lateinit var sharedPreferenceUtil: SharedPreferenceUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initControl()
        getParameters()
    }

    private fun moveToMyLocationsActivity(){
        val intent=Intent(this@MainActivity, MyLocationsActivity::class.java)
        intent.putExtra("FROM", "MainActivity")
        startActivity(intent)
        finish()
    }
    private fun initControl() {
        binding.backBtn.setOnClickListener(this)
    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.backBtn -> {
                moveToMyLocationsActivity()
                Log.i(TAG, "GO To Previous Page")
            }
        }
    }

    private fun getParameters() {
        requiredItemList = listOf("Min temp", "Max temp", "Humidity")
        if(requiredItemList.isNotEmpty()) {
            mapItemIdToItemList()
            setForecastRecyclerView()
        }
    }
    private fun mapItemIdToItemList() {
        val itemList = Gson().fromJson(sharedPreferenceUtil.itemList,
            WeatherForecast::class.java
        ).list
        ItemList = itemList
    }

    private fun setForecastRecyclerView() {
        binding.recyclerForecast.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)
        forcastAdapter =  ForecastAdapter(ItemList)
        binding.recyclerForecast.adapter = forcastAdapter
    }

    companion object {
        const val TAG = "MainActivity"
    }

}