package com.example.loginapp.model

data class PrestContas(
    val id: Long? = null,
    val descricao: String,
    val valor: Double,
    val data: String,
    val tipo: String
)