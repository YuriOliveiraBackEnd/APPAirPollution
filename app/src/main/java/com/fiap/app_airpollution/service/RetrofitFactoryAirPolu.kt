package com.example.consumoapi.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactoryAirPolu {
    private val URL = "https://api.openweathermap.org/data/2.5/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun getAirPoluService(): AirPoluService{
        return  retrofitFactory.create(AirPoluService::class.java)
    }
}