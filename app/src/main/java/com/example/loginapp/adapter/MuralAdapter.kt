package com.example.loginapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.R
import com.example.loginapp.model.Mural


class MuralAdapter(private val mural: List<Mural>) :
    RecyclerView.Adapter<MuralAdapter.MuralViewHolder>() {

    class MuralViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView = itemView.findViewById(R.id.txtmural)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuralViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mural, parent, false)

        return MuralViewHolder(view)
    }

    override fun onBindViewHolder(holder: MuralViewHolder, position: Int) {
        val mural = mural[position]

        holder.nome.text = mural.nome

    }

    override fun getItemCount(): Int {
        return mural.size
    }
}