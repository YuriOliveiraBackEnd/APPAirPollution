package com.fiap.app_airpollution.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fiap.app_airpollution.R
import com.fiap.app_airpollution.ui.theme.App_AirPollutionTheme
import com.fiap.app_airpollution.viewModel.CoordenadasScreanViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoordenadasScrean(navControler: NavController,coordenadasScreanViewModel: CoordenadasScreanViewModel ) {

    val statelugar by coordenadasScreanViewModel.statelugar.observeAsState(initial = "")
    val showMessageNulo by coordenadasScreanViewModel.showMessageNulo.observeAsState(initial = false)
    val showMessageNExiste by coordenadasScreanViewModel.showMessageNExise.observeAsState(initial = false)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .height(160.dp)
            .background(Color(0xffbbdb63))
            .offset(y = (35).dp),
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(id = R.drawable.logosemfundo),
            contentDescription = "logo", modifier = Modifier
                .size(width = 300.dp, height = 160.dp)
                .padding(bottom = 16.dp),
            alignment = Alignment.Center
        )
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = "Digite o nome de uma localidade",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .offset(y = 31.dp),
            color = Color(0xff2A5A26),
        )
        TextField(
            value = statelugar,
            onValueChange = { coordenadasScreanViewModel.onStatelugarChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 20.dp),
            textStyle = TextStyle(fontSize = 20.sp),
            placeholder = {
                Text(text = "Digite aqui")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    tint = Color.Black
                )
            },
            trailingIcon = {
                if (statelugar.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear Icon",
                        tint = Color.Black,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable { coordenadasScreanViewModel.Clear() }
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (statelugar.isNotEmpty()) {
                        coordenadasScreanViewModel.consumirApi(navControler)
                    }else {
                    coordenadasScreanViewModel.ShowTrueNulo()
                    }
                },
            ),
            shape = RoundedCornerShape(10.dp),
            interactionSource = remember { MutableInteractionSource() },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
    if (showMessageNulo) {
        Snackbar(
            action = {

            },
            backgroundColor = Color(0xff2A5A26),
            elevation = 4.dp,
            modifier = Modifier.fillMaxWidth().height(80.dp)

        ) {
            Text(text = "O campo não pode estar vazio", color = Color.White)
        }
        LaunchedEffect(true) {
            // Aguarde um atraso de 3 segundos antes de ocultar o Snackbar
            delay(2000)
            coordenadasScreanViewModel.ShowFalseNulo()
        }
    }
    if (showMessageNExiste) {
        Snackbar(
            action = {

            },
            backgroundColor = Color(0xff2A5A26),
            elevation = 4.dp,
            modifier = Modifier.fillMaxWidth().height(80.dp)

        ) {
            Text(text = "Esse lugar não existe no nosso banco de dados.", color = Color.White)
        }
        LaunchedEffect(true) {
            // Aguarde um atraso de 3 segundos antes de ocultar o Snackbar
            delay(3000)
            coordenadasScreanViewModel.ShowFalseNexiste()
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview2() {
    val navController1 = rememberNavController()
    App_AirPollutionTheme {

        CoordenadasScrean(navController1,CoordenadasScreanViewModel())
    }


}