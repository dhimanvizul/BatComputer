package com.example.batcomputer

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.batcomputer.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        sharedPreferences = getSharedPreferences(getString(R.string.preference_passcode), Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn",false)

        setContentView(binding.root)

        if (isLoggedIn){
            val intent = Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        val validPassword = sharedPreferences.getString("passcode","NULL")

        binding.apply {
            tvForgotPassword.setOnClickListener {
                val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
                startActivity(intent)
            }
            btnEnter.setOnClickListener {
                val id = binding.etId.text.toString()
                val password = binding.etPassword.text.toString()

                if(etId.text.toString().isEmpty() && etPassword.text.toString().isEmpty()) {
                    Toast.makeText(this@LoginActivity, "Fill both Fields", Toast.LENGTH_LONG).show()
                }
                else {
                    if(id == "Bruce" && password == validPassword) {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this@LoginActivity, "Wrong Credentials", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
    override fun onPause() {
        super.onPause()
        finish()
    }
}