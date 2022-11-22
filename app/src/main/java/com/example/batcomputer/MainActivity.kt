package com.example.batcomputer

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.batcomputer.databinding.ActivityMainBinding
import com.example.batcomputer.gadgets.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferences2: SharedPreferences
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var sqLiteHelper: SQLiteHelper

    private val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
    private val currentDate: String = sdf.format(Date())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        sharedPreferences2 = getSharedPreferences(getString(R.string.preference_passcode),Context.MODE_PRIVATE)

        val passwd = sharedPreferences2.getString("passcode","NULL")
        sqLiteHelper = SQLiteHelper(this)

        binding.tvCaseCount.text = getSize()
        binding.tvLastLogin.text = "Last Seen:\n "+(sharedPreferences.getString("date","null"))
        binding.tvDateTime.text = currentDate

        savePreferences(currentDate)

        binding.cvWelcome.setOnClickListener {
            Toast.makeText(this,"I'm Vengeance",Toast.LENGTH_SHORT).show()
        }
        binding.cvCriminals.setOnClickListener {
            val intent = Intent(this@MainActivity, ActivityCriminalRecord::class.java)
            startActivity(intent)
        }
        binding.btnLogout.setOnClickListener {
            val intent  = Intent(this@MainActivity, LoginActivity::class.java)
            sharedPreferences2.edit().clear().apply()
            savePreferences(currentDate)
            startActivity(intent)
            finish()
            sharedPreferences2.edit().putString("passcode",passwd).apply()
        }
        drawerLayout = findViewById(R.id.drawerLayout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.mBatComp -> {
                    val intent = Intent(this@MainActivity, BatCompActivity::class.java)
                    startActivity(intent)
                }
                R.id.mBatCape -> {
                    val intent = Intent(this@MainActivity, BatCapeActivity::class.java)
                    startActivity(intent)
                }
                R.id.mBatMobile -> {
                    val intent = Intent(this@MainActivity, BatMobileActivity::class.java)
                    startActivity(intent)
                }
                R.id.mBatCycle -> {
                    val intent = Intent(this@MainActivity, BatCycleActivity::class.java)
                    startActivity(intent)
                }
                R.id.mBelt -> {
                    val intent = Intent(this@MainActivity, BatBeltActivity::class.java)
                    startActivity(intent)
                }
                R.id.mBatSuit -> {
                    val intent = Intent(this@MainActivity, BatSuitActivity::class.java)
                    startActivity(intent)
                }
                R.id.mBatarang -> {
                    val intent = Intent(this@MainActivity, BatRangActivity::class.java)
                    startActivity(intent)
                }
                R.id.mGrapple -> {
                    val intent = Intent(this@MainActivity, GrappleGunActivity::class.java)
                    startActivity(intent)
                }
                R.id.mKSpear -> {
                    val intent = Intent(this@MainActivity, KrptoniteSpearActivity::class.java)
                    startActivity(intent)
                }
            }
            binding.drawerLayout.closeDrawers()
            return@setNavigationItemSelectedListener true
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
    private fun savePreferences(date: String) {
        sharedPreferences.edit().putString("date",date).apply()
    }
    private fun getSize(): String {
        val list = sqLiteHelper.getCrimeData()
        return "Total Cases:\n"+list.size.toString()
    }

    override fun onResume() {
        super.onResume()

        binding.tvCaseCount.text = getSize()
    }
}