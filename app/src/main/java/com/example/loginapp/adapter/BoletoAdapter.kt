package com.example.loginapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.loginapp.R
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.Boleto

class BoletoAdapter(val boletos: List<com.example.loginapp.model.Boleto>) : RecyclerView.Adapter<BoletoAdapter.BoletoViewHolder>(){
    class BoletoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val descricao : TextView= itemView.findViewById<TextView>(R.id.txtdescricao)
        val valor : TextView = itemView.findViewById<TextView>(R.id.txtvalor)
        val vencimento : TextView = itemView.findViewById<TextView>(R.id.txtvencimento)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BoletoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_boleto, parent, false)
        return BoletoAdapter.BoletoViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: BoletoViewHolder,
        position: Int
    ) {
       val boleto = boletos[position]
        holder.descricao.text = boleto.descricao
        holder.valor.text = boleto.valor.toString()
        holder.vencimento.text = boleto.vencimento

    }

    override fun getItemCount(): Int {
        class BoletoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val taxa: TextView = itemView.findViewById(R.id.txtdescricao)
            val valor: TextView = itemView.findViewById(R.id.txtvalor)
            val vencimento: TextView = itemView.findViewById(R.id.txtvencimento)
        } return (naoseioq)

    }






}