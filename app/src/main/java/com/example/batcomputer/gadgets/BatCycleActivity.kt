package com.example.batcomputer.gadgets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.batcomputer.R

class BatCycleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bat_cycle)

        title = "BatCycle"
    }
}