package com.example.consumoapi.service


import com.example.consumoapi.model.Coordenadas
import com.example.consumoapi.model.AirQualityResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AirPoluService {


    @GET("air_pollution?")
    fun getAirPolu(
    @Query("lat") lat: String,
    @Query("lon") lon: String,
    @Query("appid") appid: String = "33bead2c309049ca821a952d8eaaf513"
    ): Call <AirQualityResponse>

}
