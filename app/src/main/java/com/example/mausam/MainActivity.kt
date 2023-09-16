package com.example.mausam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mausam.databinding.ActivityMainBinding
import com.example.mausam.databinding.ActivityMyLocationBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }


}