package com.example.weatherapp.ui.hourlyForecasts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherType
import com.example.weatherapp.ui.animation.radialGradient
import com.example.weatherapp.ui.theme.WeatherAppTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun RowElement(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
){

    val formattedTime = remember(weatherData) {
        weatherData.time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )

    }

    Row(
        modifier = modifier
            .background(
                radialGradient(
                    colorStart = MaterialTheme.colorScheme.primary,
                    colorEnd = MaterialTheme.colorScheme.onSecondary,
                    dimenssionFactor = 4.0
                )
            )
            .fillMaxWidth()
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ){
        Column(
            modifier = Modifier
//                .background(radialGradient(
//                    colorStart = MaterialTheme.colorScheme.onSurfaceVariant,
//                    colorEnd = MaterialTheme.colorScheme.outline
//                ))
                ,
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Text(
                modifier = Modifier,
                text = formattedTime,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                color = Color.Black
            )
            Text(
                modifier = Modifier,
                text = "29 August",
                color = Color.Gray
            )
            
        }
        
        Text(modifier = Modifier,
            text = "${weatherData.temperature}ºC",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight
        )

        Image(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier.width(40.dp)
        )


        
        
    }
}
@Composable
@Preview
fun RowElementPreview(){
    WeatherAppTheme {
        val data = WeatherData(
            time = LocalDateTime.now(),
            temperature = 25.2,
            pressure = 1000.0,
            humidity = 56.0,
            weatherType = WeatherType.fromWMO(0),
            windSpeed = 12.0
        )
        RowElement(weatherData = data)
    }
}