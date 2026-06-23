package com.example.loginapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.R
import com.example.loginapp.model.Boleto

class BoletoAdapter(private val boletos: List<Boleto>) :
    RecyclerView.Adapter<BoletoAdapter.BoletoViewHolder>() {

    class BoletoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView = itemView.findViewById(R.id.txtdescricao)
        val valor: TextView = itemView.findViewById(R.id.txtvalor)

        val vencimento: TextView = itemView.findViewById(R.id.txtvencimento)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoletoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_boleto, parent, false)

        return BoletoViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoletoViewHolder, position: Int) {
        val boleto = boletos[position]

        holder.nome.text = boleto.descricao
        holder.valor.text = boleto.valor.toString()
        holder.vencimento.text = boleto.vencimento
    }

    override fun getItemCount(): Int {
        return boletos.size
    }
}