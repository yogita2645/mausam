package com.example.mausam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mausam.databinding.ActivityHomeBinding
import com.example.mausam.response.ListItem

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initControl()
    }

    fun getCity():String{
        val city = binding.etCity.text.toString()
        Log.d("City",city)
        return city
    }


    private fun moveToResultsActivity(){
        val intent=Intent(this@HomeActivity, ResultsActivity::class.java).also {
            it.putExtra("City",getCity())
            startActivity(it)
        }
    }
    private fun initControl() {
        binding.searchBtn.setOnClickListener(this)
    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.search_btn -> {
                moveToResultsActivity()
                Log.i(TAG, "GO To Previous Page")
            }
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }

}