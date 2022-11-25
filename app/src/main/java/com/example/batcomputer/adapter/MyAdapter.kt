package com.example.batcomputer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.batcomputer.R
import com.example.batcomputer.data.SuperHero

class MyAdapter(private val heroes: ArrayList<SuperHero>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var onItemClick: ((SuperHero) -> Unit)? = null

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.imgHero)
        val tvName: TextView = itemView.findViewById(R.id.tv1)
        val tvMail: TextView = itemView.findViewById(R.id.tv2)
        val tvType: TextView = itemView.findViewById(R.id.tv3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.img.setImageResource(heroes[position].image)
        holder.tvName.text = heroes[position].name
        holder.tvMail.text = heroes[position].mail
        holder.tvType.text = heroes[position].type

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(heroes[position])
        }
    }

    override fun getItemCount() = heroes.size
}