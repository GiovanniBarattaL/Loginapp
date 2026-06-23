package com.example.loginapp

import androidx.appcompat.widget.Toolbar
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.app.DatePickerDialog
import android.widget.Button
import android.widget.Toast
import java.util.Calendar
import com.google.android.material.textfield.TextInputEditText

class AutorizarVisitante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_autorizar_visitante)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
        val edtNome = findViewById<TextInputEditText>(R.id.edtNome)
        val edtDocumento =
            findViewById<TextInputEditText>(R.id.edtDocumento)

        val edtApartamento =
            findViewById<TextInputEditText>(R.id.edtApartamento)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        val toolbar = findViewById<Toolbar>(R.id.toolbarAutorizar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val btnData = findViewById<Button>(R.id.btnData)

        btnSalvar.setOnClickListener {

            val nome = edtNome.text.toString().trim()
            val documento = edtDocumento.text.toString().trim()
            val apartamento = edtApartamento.text.toString().trim()

            if (nome.isEmpty() ||
                documento.isEmpty() ||
                apartamento.isEmpty() ||
                btnData.text == "Selecionar Data"
            ) {

                Toast.makeText(
                    this,
                    "Preencha todos os campos!",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                Toast.makeText(
                    this,
                    "Visitante autorizado com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        btnData.setOnClickListener {

            val calendario = Calendar.getInstance()

            val dialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->

                    val data = "$dayOfMonth/${month + 1}/$year"
                    btnData.text = data

                },
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)
            )

            dialog.show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}