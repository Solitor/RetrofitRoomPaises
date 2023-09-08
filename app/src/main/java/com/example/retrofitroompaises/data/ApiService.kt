package com.example.retrofitroompaises.data


import com.example.retrofitroompaises.model.CountryJSON
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("v3.1/all")
    suspend fun getCountryJSON(): Response<ArrayList<CountryJSON>>
}
