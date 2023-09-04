package com.example.weatherapp.ui.SearchScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.R
import com.example.weatherapp.ui.states.WeatherState

@Composable
fun SearchScreen(
    modifier : Modifier = Modifier,
    state: WeatherState
){
    Column(verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Under construction Search screen")
        Image(
            painter = painterResource(id = R.drawable.ic_under_construction),
            contentDescription = "Under construction"
        )
    }
}
