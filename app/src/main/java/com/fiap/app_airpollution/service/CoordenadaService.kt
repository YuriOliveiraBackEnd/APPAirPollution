package com.example.consumoapi.service

import com.example.consumoapi.model.Coordenadas
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//chave api

interface CoordenadaService {
    //http://api.openweathermap.org/geo/1.0/direct?q={city name},{state code},{country code}&limit={limit}&appid={API key}

    @GET("direct?")
    fun getLatLon(
        @Query("q") lugar: String,
        @Query("limit") limit: Int = 1,
        @Query("appid") appid: String = "33bead2c309049ca821a952d8eaaf513"
    ): Call <List<Coordenadas>>
    }



