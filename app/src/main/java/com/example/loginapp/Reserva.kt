package com.example.loginapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.adapter.ReservaAdapter

class Reserva : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reserva)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbarreserva = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarreserva)
        setSupportActionBar(toolbarreserva)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val rc = findViewById<RecyclerView>(R.id.recyclerReserva)
        rc.layoutManager = LinearLayoutManager(this)
        val Reserva = listOf(
            com.example.loginapp.model.Reserva("Academia"),
            com.example.loginapp.model.Reserva("Brinquedoteca"),
            com.example.loginapp.model.Reserva("Churrasqueira"),
            com.example.loginapp.model.Reserva("Salão de Festa"),
            com.example.loginapp.model.Reserva("Salão de Jogos")
        )
        rc.adapter = ReservaAdapter(Reserva)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // Volta para a tela anterior
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


