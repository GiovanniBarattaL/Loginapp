package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        val textView = findViewById<TextView>(R.id.textView3)
        val nome = intent.getStringExtra("nomeusuario")
        textView.text = "Olá $nome"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnBoleto = findViewById<Button>(R.id.btnBoleto)
        btnBoleto.setOnClickListener {
            val intent = Intent(this, Boleto::class.java)
            startActivity(intent)
    }
        val btnReserva = findViewById<Button>(R.id.btnReserva)
        btnReserva.setOnClickListener {
            val intent = Intent(this, Reserva::class.java)
            startActivity(intent)
        }
        val btnAutorizarVisitante = findViewById<Button>(R.id.btnAutorizacao)
        btnAutorizarVisitante.setOnClickListener {
            val intent = Intent(this, AutorizarVisitante::class.java)
            startActivity(intent)
        }
        val btnMural= findViewById<Button>(R.id.btnMural)
        btnMural.setOnClickListener {
            val intent = Intent(this, Mural::class.java)
            startActivity(intent)
        }
        val btnPrestarConta= findViewById<Button>(R.id.btnPrestContas)
        btnPrestarConta.setOnClickListener {
            val intent = Intent(this, PrestarConta::class.java)
            startActivity(intent)
        }
        val btnContatos= findViewById<Button>(R.id.btnContatoPort)
        btnContatos.setOnClickListener {
            val intent = Intent(this, Contatos::class.java)
            startActivity(intent)
        }
        val btnMudancas= findViewById<Button>(R.id.btnMudancas)
        btnMudancas.setOnClickListener {
            val intent = Intent(this, Mudancas::class.java)
            startActivity(intent)
        }
       }
}