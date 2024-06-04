package com.fiap.app_airpollution.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.consumoapi.model.AirQualityResponse
import com.example.consumoapi.service.RetrofitFactoryAirPolu
import com.fiap.app_airpollution.R
import com.fiap.app_airpollution.ui.theme.App_AirPollutionTheme
import com.fiap.app_airpollution.viewModel.AirPolluScreanViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun AirPolluScrean(lat: String, lon: String,lugar: String ,navControler: NavController,airPolluScreanViewModel: AirPolluScreanViewModel) {
    val nivel by airPolluScreanViewModel.nivel.observeAsState(initial = 0)



    airPolluScreanViewModel.consumirApi(lat,lon)

    var cordefundo = airPolluScreanViewModel.BackgroudDaBarra(nivel)
    val corDeFundo = Color(android.graphics.Color.parseColor(cordefundo))
    var texonivel = airPolluScreanViewModel.textoNivel(nivel)

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color(0xffC0D572)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            Image(
                painter = painterResource(id = R.drawable.airguardian),
                contentDescription = "logo",
                modifier = Modifier.size(160.dp),
                contentScale = ContentScale.Crop
            )

        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "${nivel}",
            color = Color(0xff2A5A26),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Card(
            modifier = Modifier
                .size(
                    width = 350.dp,
                    height = 40.dp
                ),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(100.dp),
            colors = CardDefaults.cardColors(containerColor = corDeFundo)
        ) {

        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = texonivel,
            color = Color(0xff2A5A26),
            fontSize = 35.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Spacer(modifier = Modifier.height(60.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.width(260.dp)
            ) {
                Icon(
                    painterResource(id = R.drawable.location), contentDescription = "Custom Icon",
                    modifier = Modifier
                        .size(width = 26.dp, height = 26.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(), tint = Color.Red
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = lugar,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .width(130.dp)
                        .height(50.dp)
                        .offset(y = 12.dp)
                )
                IconButton(onClick = { navControler.navigate("CoordenadasScrean") }) {
                    Icon(imageVector =  Icons.Default.Edit , contentDescription = "editar"
                        , tint = Color(0xff2A5A26))
                }


            }






            Spacer(modifier = Modifier.height(50.dp))

        Column(
            modifier = Modifier.size(width = 200.dp, height = 200.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            Image(
                painter = painterResource(id = R.drawable.nuvem),
                contentDescription = "nuvem de poluição",
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.FillBounds
            )

        }
        Spacer(modifier = Modifier.height(70.dp))

        Button(
            onClick = { navControler.navigate("ComponentesScrean/$lat/$lon/$lugar")},
            colors = ButtonDefaults.buttonColors(Color(0xffD9D9D9)),
            modifier = Modifier
                .width(290.dp)
                .height(60.dp),
            shape = RoundedCornerShape(2.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Componentes de Poluição",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,

                )
                Spacer(modifier = Modifier.width(15.dp))
                Icon(
                    painterResource(id = R.drawable.rightarrow),
                    contentDescription = "Custom Icon",
                    modifier = Modifier.size(50.dp),
                    tint = Color.Black
                )
            }
        }










        }

    }


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    val navController1 = rememberNavController()
    App_AirPollutionTheme {
        var lat: String =""
        var lon: String = ""
        var lugar: String = "Rio de janeiro"
        AirPolluScrean(lat,lon,lugar,navController1, AirPolluScreanViewModel())
    }


}






