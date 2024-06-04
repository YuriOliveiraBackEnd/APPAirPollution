package com.fiap.app_airpollution.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fiap.app_airpollution.R
import com.fiap.app_airpollution.ui.theme.App_AirPollutionTheme
import com.fiap.app_airpollution.viewModel.AirPolluScreanViewModel

@Composable
fun ComponentesScrean(lat: String, lon: String,lugar: String,navControler: NavController,airPolluScreanViewModel: AirPolluScreanViewModel){
    val lista by airPolluScreanViewModel.listacomp.observeAsState(emptyList())

    airPolluScreanViewModel.consumirApi(lat,lon)


    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().background(
    Color.White)) {
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

    Row (horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()){
        IconButton(onClick = { navControler.navigate("AirPolluScrean/$lat/$lon/$lugar") }) {
            Icon(imageVector =  Icons.Default.Clear , contentDescription = "Fechar"
                , tint = Color(0xff2A5A26))
        }
    }

    Text(
        text = "Componentes de Poluição",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xff2A5A26),
        modifier = Modifier.offset(y = 60.dp)
        )
    Spacer(modifier = Modifier.height(32.dp))
    Text(
        text = "Principais",
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        modifier = Modifier.offset(y = 60.dp)
    )
    Spacer(modifier = Modifier.height(32.dp))
    Column(modifier = Modifier.size(height = 300.dp, width = 280.dp)) {
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                text = "CO",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.offset(y = 60.dp)
            )
            Text(
                text = "${lista.getOrNull(0)?: "Carregando..."}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.offset(y = 60.dp)
            )
        }
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                text = "NO",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.offset(y = 60.dp)
            )
            Text(
                text = "${lista.getOrNull(1)?: "Carregando..."}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.offset(y = 60.dp)
            )
        }
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                text = "NO2",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.offset(y = 60.dp)
            )
            Text(
                text = "${lista.getOrNull(2)?: "Carregando..."}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.offset(y = 60.dp)
            )
        }
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                text = "O3",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.offset(y = 60.dp)
            )
            Text(
                text = "${lista.getOrNull(3)?: "Carregando..."}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.offset(y = 60.dp)
            )
        }
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                text = "SO2",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.offset(y = 60.dp)
            )
            Text(
                text = "${lista.getOrNull(4)?: "Carregando..."}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.offset(y = 60.dp)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "PM2",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.offset(y = 60.dp)
            )
            Text(
                text = "${lista.getOrNull(5)?: "Carregando..."}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.offset(y = 60.dp)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "PM10",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.offset(y = 60.dp)
            )
            Text(
                text = "${lista.getOrNull(6)?: "Carregando..."}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.offset(y = 60.dp)
            )
        }
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                text = "NH3",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.offset(y = 60.dp)
            )
            Text(
                text = "${lista.getOrNull(7)?: "Carregando..."}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.offset(y = 60.dp)
            )
        }
    }
}

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview3() {
    App_AirPollutionTheme {
        val navController1 = rememberNavController()
        var lat: String =""
        var lon: String = ""
        var lugar: String = ""
        ComponentesScrean(lat,lon,lugar,navController1, AirPolluScreanViewModel())
    }


}

