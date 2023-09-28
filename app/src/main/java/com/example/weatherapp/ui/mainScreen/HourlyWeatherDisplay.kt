package com.example.weatherapp.ui.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherType
import com.example.weatherapp.ui.animation.radialGradient
import com.example.weatherapp.ui.theme.WeatherAppTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HourlyWeatherDisplay(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {
//    sa il pun in mapper
    val formattedTime = remember(weatherData) {
          weatherData.time.format(
                DateTimeFormatter.ofPattern("HH:mm")
            )

    }
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp) )
            .widthIn(min= 80.dp)

            .background(radialGradient(colorStart = MaterialTheme.colorScheme.outlineVariant, colorEnd = MaterialTheme.colorScheme.outline))
            .padding(8.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = formattedTime.takeLast(5),
            color = MaterialTheme.colorScheme.background
        )
        Image(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier.width(40.dp)
        )
        Text(
            text = "${weatherData.temperature}°C",
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun HourlyPreview(){
    WeatherAppTheme {
       
        val data = WeatherData(
            time = LocalDateTime.now().toString().takeLast(5),
            temperature = 25.2,
            pressure = 1000.0,
            humidity = 56.0,
            weatherType = WeatherType.fromWMO(0),
            windSpeed = 12.0
        )
        HourlyWeatherDisplay(weatherData = data)
    }
}
