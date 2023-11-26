package com.example.mausam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mausam.databinding.RvHourlyForecastBinding
import com.example.mausam.response.ListItem
import java.lang.Math.ceil


class ForecastAdapter(
    private val itemList: List<ListItem>,
) : RecyclerView.Adapter<ForecastAdapter.ForecastItemView>() {


    class ForecastItemView(val binding: RvHourlyForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun onBind(item: ListItem) {
            val context = binding.root.context
            var tempo= item.dt_txt
            var currTemp =(ceil(item.main.temp)-273.00).toString()+"°C"
            binding.temp.text=(ceil(item.main.temp)-273.00).toString()+"°C"
            binding.feelsLike.text= tempo.substring(10,19)
            binding.humidity.text = item.main.humidity.toString()
            binding.description.text = item.weather[0].description
            binding.minTemp.text = (ceil(item.main.temp_min)-273.00).toString()+"°C"
            binding.maxTemp.text = (ceil(item.main.temp_max-272.00)).toString()+"°C"
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastItemView {
        return ForecastItemView(
            RvHourlyForecastBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ForecastItemView, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}