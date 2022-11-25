package com.example.batcomputer.dc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.batcomputer.data.SuperHero
import com.example.batcomputer.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val heroDet = intent.getParcelableExtra<SuperHero>("superhero")
        if(heroDet != null) {
            title = heroDet.name
            binding.tvDetail.text = heroDet.essay
            binding.imgDetail.setImageResource(heroDet.image)
        }
    }
}