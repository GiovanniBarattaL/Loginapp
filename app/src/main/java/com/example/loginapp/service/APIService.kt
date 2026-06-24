package com.example.loginapp.service
import com.example.loginapp.model.Login
import com.example.loginapp.model.Usuario
import retrofit2.Call
import com.example.loginapp.model.PrestContas
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
interface APIService {
    @POST("/cadastro")
    fun cadastrarUsuario(
        @Body usuario: Usuario
    ): Call<Usuario>
    @POST("/autenticar")
    fun autenticar(
        @Body login: Login
    ): Call<Boolean>

    @POST("/buscarporemail")
    fun buscarporemail(@Body login: Login
    ): Call<Usuario>

    @POST("/prestcontas")
    fun salvarPrestConta(
        @Body prestContas: PrestContas
    ): Call<PrestContas>

    @GET("/prestcontas")
    fun listarPrestContas():
            Call<List<PrestContas>>
}