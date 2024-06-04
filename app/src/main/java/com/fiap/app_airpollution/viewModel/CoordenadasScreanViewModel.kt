package com.fiap.app_airpollution.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.consumoapi.model.Coordenadas
import com.example.consumoapi.service.RetrofitFactoryGeoCod
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoordenadasScreanViewModel: ViewModel() {

    private val _statelugar = MutableLiveData<String>()
    val statelugar: LiveData<String> = _statelugar

    private val _showMessageNulo = MutableLiveData<Boolean>()
    val showMessageNulo: LiveData<Boolean> = _showMessageNulo

    private val _showMessageNExise = MutableLiveData<Boolean>()
    val showMessageNExise: LiveData<Boolean> = _showMessageNExise

    private val _listacod = MutableLiveData<List<Coordenadas>>()
    val listacod: LiveData<List<Coordenadas>> = _listacod

    fun onStatelugarChange(novolugar: String){
        _statelugar.value = novolugar
    }
    fun Clear(){ _statelugar.value = "" }
    fun ShowTrueNulo(){ _showMessageNulo.value = true }
    fun ShowFalseNulo(){ _showMessageNulo.value = false }
    fun ShowFalseNexiste(){ _showMessageNExise.value = false }

    fun consumirApi(navControler: NavController){

        var call = RetrofitFactoryGeoCod().getCoordenadaService()
        .getLatLon(lugar = _statelugar.value!!)
        call.enqueue(object : Callback<List<Coordenadas>> {
            override fun onResponse(
                call: Call<List<Coordenadas>>,
                response: Response<List<Coordenadas>>
            ) {
                if (response.isSuccessful) {
                    _listacod.value = response.body()
                    if (_listacod.value != null && _listacod.value!!.isNotEmpty()) {
                        val coordenadas = _listacod.value!![0]
                        val lat = coordenadas.lat
                        val lon = coordenadas.lon
                        navControler.navigate("AirPolluScrean/$lat/$lon/${_statelugar.value}")
                    } else {
                        // Lista de coordenadas é nula ou vazia
                        _showMessageNExise.value = true
                    }
                }

            }


            override fun onFailure(call: Call<List<Coordenadas>>, t: Throwable) {
                Log.i("Fiap", "onResponse: ${t.message}")
            }
        })


}


}