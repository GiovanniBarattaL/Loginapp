package com.example.loginapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.R
import com.example.loginapp.model.Boleto
import com.example.loginapp.model.PrestContas

class PrestContasAdapter(private val prestcontas: List<PrestContas>) :
    RecyclerView.Adapter<PrestContasAdapter.PrestContasViewHolder>() {

    class PrestContasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView = itemView.findViewById(R.id.txtDescricaoconta)
        val valor: TextView = itemView.findViewById(R.id.txtValorconta)
        val data: TextView = itemView.findViewById(R.id.txtDataconta)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrestContasViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_prestcontas, parent, false)

        return PrestContasViewHolder(view)
    }

    override fun onBindViewHolder(holder: PrestContasViewHolder, position: Int) {
        val prestcontas = prestcontas[position]

        holder.nome.text = prestcontas.descricao
        holder.valor.text = prestcontas.valor.toString()
        holder.data.text = prestcontas.data

    }

    override fun getItemCount(): Int {
        return prestcontas.size
    }
}