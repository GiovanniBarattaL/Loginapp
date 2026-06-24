package com.example.loginapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.LayoutInflater
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialog
import android.app.DatePickerDialog
import java.util.Calendar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.adapter.BoletoAdapter
import com.example.loginapp.adapter.PrestContasAdapter
import com.example.loginapp.model.Boleto
import com.example.loginapp.model.PrestContas
import com.example.loginapp.network.ApiClient

class PrestarConta : AppCompatActivity() {

    private lateinit var txtRecebido: TextView
    private lateinit var txtGasto: TextView
    private lateinit var txtSaldo: TextView

    private var totalRecebido = 0.0
    private var totalGasto = 0.0

    private lateinit var adapter: PrestContasAdapter
    private val listaPrestContas =
        mutableListOf<PrestContas>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_prestar_conta)

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

        val rc =
            findViewById<RecyclerView>(
                R.id.recyclerPrestContas
            )
        adapter =
            PrestContasAdapter(listaPrestContas)

        rc.layoutManager =
            LinearLayoutManager(this)

        rc.adapter = adapter

        val toolbar = findViewById<Toolbar>(R.id.toolbarPrestarConta)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        txtRecebido = findViewById(R.id.txtRecebido)
        txtGasto = findViewById(R.id.txtGasto)
        txtSaldo = findViewById(R.id.txtSaldo)

        val btnAdicionar = findViewById<Button>(R.id.btnAdicionar)
        btnAdicionar.setOnClickListener {
            mostrarDialogLancamento()
        }

        atualizarCards()
        carregarLancamentos()
    }
    private fun mostrarDialogLancamento() {

        val dialog = BottomSheetDialog(this)

        val view = LayoutInflater.from(this)
            .inflate(R.layout.lancamento, null)

        dialog.setContentView(view)

        val edtDescricao =
            view.findViewById<com.google.android.material.textfield.TextInputEditText>(
                R.id.edtDescricao
            )

        val edtValor =
            view.findViewById<com.google.android.material.textfield.TextInputEditText>(
                R.id.edtValor
            )

        val btnData =
            view.findViewById<Button>(R.id.btnDataLancamento)

        val rbEntrada =
            view.findViewById<android.widget.RadioButton>(R.id.rbEntrada)

        val rbSaida =
            view.findViewById<android.widget.RadioButton>(R.id.rbSaida)

        val btnSalvar =
            view.findViewById<Button>(R.id.btnSalvarLancamento)

        // BOTÃO DA DATA
        btnData.setOnClickListener {

            val calendario = Calendar.getInstance()

            val dialogData = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->

                    val data = "%02d/%02d/%04d".format(
                        dayOfMonth,
                        month + 1,
                        year
                    )

                    btnData.text = data
                },
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)
            )

            dialogData.show()
        }

        // BOTÃO SALVAR
        btnSalvar.setOnClickListener {

            val descricao =
                edtDescricao.text.toString().trim()

            val valorTexto =
                edtValor.text.toString().trim()

            if (descricao.isEmpty() || valorTexto.isEmpty()) {
                Toast.makeText(
                    this,
                    "Preencha todos os campos.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (btnData.text.toString() == "Selecionar Data") {
                Toast.makeText(
                    this,
                    "Selecione uma data.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (!rbEntrada.isChecked && !rbSaida.isChecked) {
                Toast.makeText(
                    this,
                    "Selecione Entrada ou Saída.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val valor = valorTexto.toDouble()

            val tipo =
                if (rbEntrada.isChecked)
                    "Entrada"
                else
                    "Saída"

            val lancamento =
                PrestContas(
                    descricao = descricao,
                    valor = valor,
                    data = btnData.text.toString(),
                    tipo = tipo
                )
            ApiClient.instance
                .salvarPrestConta(lancamento)
                .enqueue(object :
                    retrofit2.Callback<PrestContas> {

                    override fun onResponse(
                        call: retrofit2.Call<PrestContas>,
                        response: retrofit2.Response<PrestContas>
                    ) {

                        if (response.isSuccessful) {

                            Toast.makeText(
                                this@PrestarConta,
                                "Lançamento salvo!",
                                Toast.LENGTH_SHORT
                            ).show()

                            carregarLancamentos()
                            dialog.dismiss()
                        }
                    }

                    override fun onFailure(
                        call: retrofit2.Call<PrestContas>,
                        t: Throwable
                    ) {

                        Toast.makeText(
                            this@PrestarConta,
                            "Erro ao salvar.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }

        dialog.show()
    }

    private fun atualizarCards() {
        val saldo = totalRecebido - totalGasto

        txtRecebido.text = "R$ %.2f".format(totalRecebido)
        txtGasto.text = "R$ %.2f".format(totalGasto)
        txtSaldo.text = "R$ %.2f".format(saldo)
    }

    private fun carregarLancamentos() {

        ApiClient.instance
            .listarPrestContas()
            .enqueue(object :
                retrofit2.Callback<List<PrestContas>> {

                override fun onResponse(
                    call: retrofit2.Call<List<PrestContas>>,
                    response: retrofit2.Response<List<PrestContas>>
                ) {

                    if (response.isSuccessful) {

                        val lista =
                            response.body() ?: emptyList()

                        listaPrestContas.clear()
                        listaPrestContas.addAll(lista)

                        adapter.notifyDataSetChanged()

                        totalRecebido = 0.0
                        totalGasto = 0.0

                        for (item in lista) {

                            if (item.tipo == "Entrada") {
                                totalRecebido += item.valor
                            } else {
                                totalGasto += item.valor
                            }
                        }

                        atualizarCards()
                    }
                }

                override fun onFailure(
                    call: retrofit2.Call<List<PrestContas>>,
                    t: Throwable
                ) {

                    Toast.makeText(
                        this@PrestarConta,
                        "Erro ao carregar dados",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}