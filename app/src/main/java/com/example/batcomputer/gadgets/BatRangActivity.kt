package com.example.batcomputer.gadgets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.batcomputer.R
import com.example.batcomputer.databinding.ActivityBatRangBinding

class BatRangActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBatRangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBatRangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "BatRang"
    }
}