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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherType
import com.example.weatherapp.ui.animation.radialGradient
import com.example.weatherapp.ui.mainScreen.WeatherDataDisplay
import com.example.weatherapp.ui.theme.WeatherAppTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun CurrentHour(
    modifier : Modifier  = Modifier,
    data: WeatherData

){
    val formattedTime = remember(data) {
       data.time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )


    }

    Column(
        modifier = modifier.fillMaxWidth()
            .background(radialGradient(
                colorEnd = MaterialTheme.colorScheme.surfaceVariant,
                colorStart = MaterialTheme.colorScheme.onSurfaceVariant,
//                dimenssionFactor = 4.0
            )),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Current hour:"
            )
            Text(
                text = formattedTime
            )
            Image(
                painter = painterResource(id = data.weatherType.iconRes),
                contentDescription = null,
                modifier = Modifier.width(40.dp)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
             verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){

            WeatherDataDisplay(
                value = data.pressure.roundToInt(),
                unit = "hpa",
                icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                iconTint = MaterialTheme.colorScheme.onPrimaryContainer,
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
            )
            WeatherDataDisplay(
                value = data.humidity.roundToInt(),
                unit = "%",
                icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                iconTint = MaterialTheme.colorScheme.onPrimaryContainer,
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
            )
            WeatherDataDisplay(
                value = data.windSpeed.roundToInt(),
                unit = "km/h",
                icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                iconTint = MaterialTheme.colorScheme.onPrimaryContainer,
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
            )

        }
    }

}

@Composable
@Preview
fun CurrentHourPreview(){
    WeatherAppTheme {
        val data = WeatherData(
            time = LocalDateTime.now().toString(),
            temperature = 25.2,
            pressure = 1000.0,
            humidity = 56.0,
            weatherType = WeatherType.fromWMO(0),
            windSpeed = 12.0
        )
      CurrentHour(data= data)
    }
}