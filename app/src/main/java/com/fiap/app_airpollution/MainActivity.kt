package com.fiap.app_airpollution

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fiap.app_airpollution.ui.theme.App_AirPollutionTheme
import com.fiap.app_airpollution.view.AirPolluScrean
import com.fiap.app_airpollution.view.ComponentesScrean
import com.fiap.app_airpollution.view.CoordenadasScrean
import com.fiap.app_airpollution.viewModel.AirPolluScreanViewModel
import com.fiap.app_airpollution.viewModel.CoordenadasScreanViewModel
import java.util.ArrayList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App_AirPollutionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "CoordenadasScrean") {
                        composable(route = "CoordenadasScrean") {
                            CoordenadasScrean(navController, CoordenadasScreanViewModel())
                        }
                        composable(route = "AirPolluScrean/{lat}/{lon}/{statelugar}") {
                            val lat = it.arguments?.getString("lat")
                            val lon = it.arguments?.getString("lon")
                            val lugar = it.arguments?.getString("statelugar")
                            if (lat != null && lon != null && lugar != null) {
                                AirPolluScrean(
                                    lat, lon, lugar, navController,
                                    AirPolluScreanViewModel()
                                )
                            } else {
                                // Lidar com o caso em que os argumentos não estão presentes
                                Log.e("Fiap", "Argumentos 'lat' e/ou 'lon' nulos")
                            }
                        }
                        composable(route = "ComponentesScrean/{lat}/{lon}/{statelugar}") {
                            val lat = it.arguments?.getString("lat")
                            val lon = it.arguments?.getString("lon")
                            val lugar = it.arguments?.getString("statelugar")
                           if (lat != null && lon != null && lugar != null)
                                ComponentesScrean( lat, lon,lugar,navController,AirPolluScreanViewModel())
                        }

                    }
                }
            }
        }
    }
}




