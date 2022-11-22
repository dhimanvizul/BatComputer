package com.example.batcomputer

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.internal.artificialFrame

class CriminalAdapter : RecyclerView.Adapter<CriminalAdapter.CriminalViewHolder>() {

    private var criminalList: ArrayList<CriminalModel> = ArrayList()
    private var onClickItem: ((CriminalModel) -> Unit)? = null
    private var onClickDeleteItem: ((CriminalModel) -> Unit)? = null

    fun addToList(items: ArrayList<CriminalModel>) {
        this.criminalList = items
        notifyDataSetChanged()
    }

    fun onSetClickItem(callback: (CriminalModel)-> Unit) {
        this.onClickItem = callback
    }
    fun onSetClickDeleteItem(callback: (CriminalModel) -> Unit) {
        this.onClickDeleteItem = callback
    }

    class CriminalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cases: TextView = itemView.findViewById(R.id.tvCase)
        private val name: TextView = itemView.findViewById(R.id.tvName)
        private val age: TextView = itemView.findViewById(R.id.tvAge)
        private val crime: TextView = itemView.findViewById(R.id.tvCrime)
        private val iYears: TextView = itemView.findViewById(R.id.tvIYears)
        private val isSolved: TextView = itemView.findViewById(R.id.tvIsSolved)
        val delButton: Button = itemView.findViewById(R.id.btnDeleteRecord)

        fun bindView(criminal: CriminalModel) {
            cases.text = criminal.case.toString()
            name.text = criminal.name
            crime.text = criminal.crime
            age.text = criminal.age.toString()
            iYears.text = criminal.imprisonment.toString()
            isSolved.text = criminal.isSolved
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CriminalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.criminal_list, parent,false)
        return CriminalViewHolder(view)
    }

    override fun onBindViewHolder(holder: CriminalViewHolder, position: Int) {
        val crime = criminalList[position]
        holder.bindView(crime)
        holder.itemView.setOnClickListener {
            onClickItem?.invoke(crime)
        }
        holder.delButton.setOnClickListener {
            onClickDeleteItem?.invoke(crime)
        }
    }

    override fun getItemCount() = criminalList.size
}
