package com.example.batcomputer

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.batcomputer.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Forgot Password"

        sharedPreferences = getSharedPreferences(getString(R.string.preference_passcode), Context.MODE_PRIVATE)

        binding.apply {
            btnConfirmName.setOnClickListener {
                if(etForgotName.text.toString() != "Bruce Wayne") {
                    Toast.makeText(this@ForgotPasswordActivity,"Incorrect",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else {
                    tvEnterName.visibility = View.GONE
                    etForgotName.visibility = View.GONE
                    btnConfirmName.visibility = View.GONE
                    textView.visibility = View.VISIBLE
                    etNewPassword.visibility = View.VISIBLE
                    etConfirmNewPassword.visibility = View.VISIBLE
                    btnSignup.visibility = View.VISIBLE
                }
            }
            btnSignup.setOnClickListener {
                if(etNewPassword.text.toString() == etConfirmNewPassword.text.toString()) {
                    sharedPreferences.edit().putString("passcode",etNewPassword.text.toString()).apply()
                    val intent = Intent(this@ForgotPasswordActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                    Toast.makeText(this@ForgotPasswordActivity,"Passwords do not match",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}