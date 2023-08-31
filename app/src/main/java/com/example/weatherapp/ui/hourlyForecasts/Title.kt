package com.example.weatherapp.ui.hourlyForecasts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun Title(
    modifier: Modifier,
    city: String? = "Current city"
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),

    ) {

//        Icon(
//            imageVector = ImageVector.vectorResource(id =R.drawable.ic_back),
//            contentDescription = null,
//            modifier = Modifier.size(25.dp)
//                .align(Alignment.TopStart)
//        )

        Text(
            text = city.toString(),
            textAlign = TextAlign.Center,
            fontSize= MaterialTheme.typography.headlineMedium.fontSize,
            modifier = Modifier.align(Alignment.TopCenter)
        )

    }

}

@Composable
@Preview
fun MenuPreview(){

    WeatherAppTheme {
        Title( modifier = Modifier, city = "Timisoara")
    }
}