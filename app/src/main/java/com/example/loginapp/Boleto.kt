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
import com.example.loginapp.adapter.BoletoAdapter
import com.example.loginapp.model.Boleto

class Boleto : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_boleto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toolbarboleto = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarboletos)
        setSupportActionBar(toolbarboleto)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val rc = findViewById<RecyclerView>(R.id.recyclerBoleto)
        rc.layoutManager = LinearLayoutManager(this)
        val boletos = listOf(
            Boleto(1,"Taxa de junho",100.0, "30/04/26"),
            Boleto(2,"Taxa de maio",12200.0, "30/12/24"),
            Boleto(3,"Taxa de dezembro",10.0, "30/09/2099"),
            Boleto(4,"Taxa de agosto",9.0, "30/05/2100")

        )
        rc.adapter = BoletoAdapter(boletos)
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


