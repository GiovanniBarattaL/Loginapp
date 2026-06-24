package com.example.loginapp

import androidx.appcompat.widget.Toolbar
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.app.DatePickerDialog
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import java.util.Calendar
import com.google.android.material.textfield.TextInputEditText

class Mudancas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mudancas)

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
        val edtNomeres = findViewById<TextInputEditText>(R.id.edtNomeres)

        val edtnapt = findViewById<TextInputEditText>(R.id.edtnapt)

        val btnSalvar = findViewById<Button>(R.id.btnCadastrarmudanca)

        val toolbarmudancas = findViewById<Toolbar>(R.id.toolbarMudancas)
        setSupportActionBar(toolbarmudancas)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val btnDatamudancas = findViewById<Button>(R.id.btnDatamudancas)

        btnSalvar.setOnClickListener {

            val nome = edtNomeres.text.toString().trim()
            val documento = edtnapt.text.toString().trim()


            if (nome.isEmpty() ||
                documento.isEmpty() ||
                btnDatamudancas.text == "Selecionar Data"
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


        btnDatamudancas.setOnClickListener {

            val calendario = Calendar.getInstance()

            val dialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->

                    val data = "$dayOfMonth/${month + 1}/$year"
                    btnDatamudancas.text = data

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