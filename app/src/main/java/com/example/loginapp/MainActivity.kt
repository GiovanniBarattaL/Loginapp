package com.example.loginapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginapp.model.Login
import com.example.loginapp.model.Usuario
import com.example.loginapp.network.ApiClient
import com.example.loginapp.service.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val apiService = ApiClient.instance
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fun buscarusuario(login: Login){
            apiService.buscarporemail(login)
                .enqueue(object: Callback<Usuario>{
                    override fun onResponse(call: Call<Usuario?>, response: Response<Usuario?>) {
                        if(response.isSuccessful){
                            val nome = response.body()?.nome
                            val intent = Intent(this@MainActivity,Home::class.java)
                            intent.putExtra("nomeusuario", nome)
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<Usuario?>, t: Throwable) {
                        Toast.makeText(this@MainActivity,"Erro ao buscar", Toast.LENGTH_SHORT).show()
                    }
                })
        }
        val btnLogin = findViewById<Button>(R.id.Loginbot)
        btnLogin.setOnClickListener {
                val email = findViewById<EditText>(R.id.emaillogin).text.toString()
                val senha = findViewById<EditText>(R.id.senhalogin).text.toString()
                val login = Login(email = email, senha = senha)


                apiService.autenticar(login).enqueue(object : Callback<Boolean> {
                    override fun onResponse(
                        call: Call<Boolean?>,
                        response: Response<Boolean?>
                    ) {
                        if (response.isSuccessful) {
                            val autenticado = response.body()
                            if (autenticado == true) {
                                buscarusuario(login)
                                val intent = Intent(this@MainActivity, Home::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Usuario ou senha inválido!!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                    }

                    override fun onFailure(call: Call<Boolean?>, t: Throwable) {
                        Toast.makeText(
                            this@MainActivity,
                            "Erro na conexão",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                })


            }
            val btncadastro = findViewById<Button>(R.id.Loginbot2)
            btncadastro.setOnClickListener {
                val intent = Intent(this, Cadastro::class.java)
                startActivity(intent)
            }
        }
    }
