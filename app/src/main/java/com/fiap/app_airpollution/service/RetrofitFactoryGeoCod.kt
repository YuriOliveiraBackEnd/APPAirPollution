package com.example.consumoapi.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactoryGeoCod {
    private val URL = "https://api.openweathermap.org/geo/1.0/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun getCoordenadaService(): CoordenadaService{
        return  retrofitFactory.create(CoordenadaService::class.java)
    }
}