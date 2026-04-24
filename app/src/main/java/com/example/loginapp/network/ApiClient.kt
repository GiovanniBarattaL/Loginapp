package com.example.loginapp.network
import com.example.loginapp.service.APIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiClient {
    private val URL = "http://192.168.56.1:8080/"

    val instance : APIService by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(APIService::class.java)

    }
}