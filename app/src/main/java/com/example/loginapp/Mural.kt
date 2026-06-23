package com.example.loginapp

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.adapter.MuralAdapter
import com.example.loginapp.adapter.ReservaAdapter

class Mural : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mural)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toolbarmural = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarMural)
        setSupportActionBar(toolbarmural)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val rc = findViewById<RecyclerView>(R.id.recyclerMural)
        rc.layoutManager = LinearLayoutManager(this)
        val Mural = listOf(
            com.example.loginapp.model.Mural("Novo Bicecletário"),
            com.example.loginapp.model.Mural("Alarme de Incêndio"),
            com.example.loginapp.model.Mural("Portão de Veículos"),
            com.example.loginapp.model.Mural("Festa Junina"),
            com.example.loginapp.model.Mural("Academia Reformada")
        )
        rc.adapter = MuralAdapter(Mural)
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