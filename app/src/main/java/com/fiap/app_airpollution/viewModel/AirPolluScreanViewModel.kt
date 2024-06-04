package com.fiap.app_airpollution.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.consumoapi.model.AirQualityResponse
import com.example.consumoapi.model.Coordenadas
import com.example.consumoapi.service.RetrofitFactoryAirPolu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AirPolluScreanViewModel: ViewModel() {
    private val _nivel = MutableLiveData<Int>()
    val nivel: LiveData<Int> = _nivel

    private val _listacomp = MutableLiveData<List<Double>>()
    val listacomp: LiveData<List<Double>> = _listacomp

    fun consumirApi(lat: String, lon: String){
        val call =
            RetrofitFactoryAirPolu().getAirPoluService()
                .getAirPolu(lat = lat, lon = lon)
        call.enqueue(object : Callback<AirQualityResponse> {
            override fun onResponse(
                call: Call<AirQualityResponse>,
                response: Response<AirQualityResponse>
            ) {
                Log.i("FIAP", "onResponse: ${response.body()}")
                val poluicao = response.body()
                val aqi = poluicao?.list?.getOrNull(0)?.main?.aqi
                _nivel.value = aqi!!
                val components = poluicao?.list?.getOrNull(0)?.components
                val componentList = mutableListOf<Double>()
                componentList.add(components?.co ?: 0.0)
                componentList.add(components?.no ?: 0.0)
                componentList.add(components?.no2 ?: 0.0)
                componentList.add(components?.o3 ?: 0.0)
                componentList.add(components?.so2 ?: 0.0)
                componentList.add(components?.pm2_5 ?: 0.0)
                componentList.add(components?.pm10 ?: 0.0)
                componentList.add(components?.nh3 ?: 0.0)

                _listacomp.value = componentList
            }



            override fun onFailure(call: Call<AirQualityResponse>, t: Throwable) {
                Log.i("Fiap", "onResponse: ${t.message}")
            }


        })

    }
    fun BackgroudDaBarra(nivelaqi: Int): String{
        var color: String
        if (nivelaqi == 1) {
            color = "#80ba41"
        }
        else if(nivelaqi ==2){
            color = "#c7d537"
        }
        else if(nivelaqi ==3){
            color = "#f6de40"
        }
        else if(nivelaqi ==4){
            color = "#f39245"
        }
        else if(nivelaqi ==5){
            color = "#e75839"
        }
        else
            color = "#FFFFFF"


        return color
    }
    fun textoNivel(nivelaqi: Int): String{
        var texto: String
        if (nivelaqi == 1) {
            texto = "Ótimo"
        }
        else if(nivelaqi ==2){
            texto = "Bom"
        }
        else if(nivelaqi ==3){
            texto = "Regular"
        }
        else if(nivelaqi ==4){
            texto = "Ruim"
        }
        else if(nivelaqi ==5){
            texto = "Muito ruim"
        }
        else
            texto = "Carregando..."


        return texto
    }





}