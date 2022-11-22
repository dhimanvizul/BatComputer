package com.example.batcomputer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.batcomputer.databinding.ActivityCriminalRecordBinding

class ActivityCriminalRecord : AppCompatActivity() {

    private lateinit var binding: ActivityCriminalRecordBinding
    private lateinit var sqLiteHelper: SQLiteHelper
    private var criminalAdapter: CriminalAdapter? = null
    private var criminalModel: CriminalModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCriminalRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Bat Memory"
        sqLiteHelper = SQLiteHelper(this)

        binding.rvCriminalRecord.layoutManager = LinearLayoutManager(this@ActivityCriminalRecord)
        criminalAdapter = CriminalAdapter()
        binding.rvCriminalRecord.adapter = criminalAdapter

        binding.apply {
            btnAddCrime.setOnClickListener {
                addCrime()
            }
            btnViewCrime.setOnClickListener {
                getCrime()
            }
            btnUpdateCrime.setOnClickListener {
                updateCrime()
            }
            btnClearField.setOnClickListener {
                etName.text.clear()
                etAge.text.clear()
                etCrime.text.clear()
                etPunishment.text.clear()
            }
        }

        criminalAdapter?.onSetClickItem {
            Toast.makeText(this@ActivityCriminalRecord, "${it.name} -> ${it.crime}", Toast.LENGTH_SHORT).show()

            binding.etName.setText(it.name)
            binding.etAge.setText(it.age.toString())
            binding.etCrime.setText(it.crime)
            binding.etPunishment.setText(it.imprisonment.toString())
            criminalModel = it
        }
        criminalAdapter?.onSetClickDeleteItem {
            deleteCrime(it.case)
        }
    }
    private fun addCrime() {
        binding.apply {
            val name = etName.text.toString()
            val age = etAge.text.toString()
            val crime = etCrime.text.toString()
            val imprison = etPunishment.text.toString()
            val isSolve = etIsSolved.isChecked.toString()

            getCrime()

            if(name.isEmpty() || age.isEmpty()||crime.isEmpty()||imprison.isEmpty()||isSolve.isEmpty()) {
                Toast.makeText(this@ActivityCriminalRecord,"Enter Required Fields", Toast.LENGTH_SHORT).show()
            }
            else if(name == criminalModel?.name && age == (criminalModel?.age).toString() && crime == criminalModel?.crime &&
                imprison == (criminalModel?.imprisonment).toString()) {
                Toast.makeText(this@ActivityCriminalRecord,"Record already exists", Toast.LENGTH_SHORT).show()
            }
            else {
                val crimes = CriminalModel(name=name,age=age.toInt(),crime=crime,imprisonment=imprison.toInt(),isSolved=isSolve)
                val status = sqLiteHelper.insertCrimeData(crimes)

                if(status >-1) {
                    clearEditText()
                    Toast.makeText(this@ActivityCriminalRecord,"Record Added",Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this@ActivityCriminalRecord,"Record Not Added",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun getCrime() {
        val crList = sqLiteHelper.getCrimeData()
        Log.d("size", "${crList.size}")

        criminalAdapter?.addToList(crList)
    }
    private fun updateCrime() {
        binding.apply {
            val name = etName.text.toString()
            val age = etAge.text.toString()
            val crime = etCrime.text.toString()
            val imprison = etPunishment.text.toString()
            val isSolve = etIsSolved.isChecked.toString()

            if (name == criminalModel?.name && age == (criminalModel?.age).toString() && crime == criminalModel?.crime &&
                imprison == (criminalModel?.imprisonment).toString()) {
                Toast.makeText(this@ActivityCriminalRecord, "Record Not Updated", Toast.LENGTH_SHORT).show()
                return
            }
            if(criminalModel == null) return

            val criminalModel = CriminalModel(case=criminalModel!!.case,name=name,age=age.toInt(),crime=crime,imprisonment=imprison.toInt(),isSolved=isSolve)
            val status = sqLiteHelper.updateCrimeData(criminalModel)
            if(status >-1) {
                clearEditText()
                getCrime()
            } else {
                Toast.makeText(this@ActivityCriminalRecord,"Update failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun deleteCrime(case: Int) {

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Sure to Remove Record?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes") { dialog,_ ->
            sqLiteHelper.deleteRecordById(case)
            getCrime()
            dialog.dismiss()
        }
        builder.setNegativeButton("No") {
                dialog,_ -> dialog.dismiss()
        }
        builder.create().show()
    }
    private fun clearEditText() {
        binding.apply {
            etName.setText("")
            etAge.setText("")
            etCrime.setText("")
            etPunishment.setText("")
        }
    }
}