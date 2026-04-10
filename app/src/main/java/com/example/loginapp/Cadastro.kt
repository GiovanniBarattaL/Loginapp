package com.example.loginapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginapp.model.Usuario
import com.example.loginapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Cadastro : AppCompatActivity() {

    val apiService = ApiClient.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var nome= findViewById<EditText>(R.id.nome)
        var email = findViewById<EditText>(R.id.email)
        var telefone = findViewById<EditText>(R.id.telefone)
        var senha = findViewById<EditText>(R.id.senha)
        var btnCadastro = findViewById<Button>(R.id.btncadastro)
        var confirmarsenha = findViewById<EditText>(R.id.confirmar)

        btnCadastro.setOnClickListener {
            var usuario = Usuario(
                nome = nome.text.toString(),
                email = email.text.toString(),
                telefone = telefone.text.toString(),
                senha = senha.text.toString()
            )
            cadastroUsuario(usuario)

        }

        }
            fun cadastroUsuario(usuario: Usuario) {
                apiService.cadastrarUsuario(usuario).enqueue(object : Callback<Usuario>{
                    override fun onResponse(call: Call<Usuario?>, response: Response<Usuario?>) {

                        if (response.isSuccessful){
                            Toast.makeText(
                                this@Cadastro,
                                "Cadastro realizado",
                                Toast.LENGTH_LONG).show()
                        }
                    }


                    override fun onFailure(call: Call<Usuario?>, t: Throwable) {
                        Toast.makeText(
                            this@Cadastro,
                            "Falha na rede: ${t.message}",
                            Toast.LENGTH_SHORT).show()
                    }

                })

            }

        }

