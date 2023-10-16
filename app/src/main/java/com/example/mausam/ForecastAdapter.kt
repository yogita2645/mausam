package com.example.mausam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mausam.databinding.RvHourlyForecastBinding
import com.example.mausam.response.ListItem


class ForecastAdapter(
    private val itemList: List<ListItem>,
) : RecyclerView.Adapter<ForecastAdapter.ForecastItemView>() {


    class ForecastItemView(val binding: RvHourlyForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun onBind(item: ListItem) {
            val context = binding.root.context
            binding.time.text="14.20"
            binding.humidity.text = item.main.humidity.toString()
            binding.description.text = "clear sky"
            binding.minTemp.text = item.main.temp_min.toString()
            binding.maxTemp.text = item.main.temp_max.toString()
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