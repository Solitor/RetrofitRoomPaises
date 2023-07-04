package com.example.retrofitroompaises.data


import com.example.retrofitroompaises.model.PaisJSON
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("[]") // Replace with your API endpoint
    suspend fun getPaisesJSON(): Response<ArrayList<PaisJSON>> // Adjust the response type as per your API response
}
