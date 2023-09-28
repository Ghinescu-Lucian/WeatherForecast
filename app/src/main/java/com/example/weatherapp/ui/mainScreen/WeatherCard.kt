package com.example.weatherapp.ui.mainScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherType
import com.example.weatherapp.ui.animation.radialGradient
import java.time.LocalDateTime
import kotlin.math.roundToInt


@Composable
fun WeatherCard(

    data: WeatherData?,
    modifier: Modifier = Modifier
){



    if(data == null) {
        // text de eroare
        Log.d("DEBUG", "CEVA")
        return
    }


        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp),
//            colors = CardDefaults.cardColors(containerColor = radialGradient(
//                MaterialTheme.colorScheme.primary,
//                MaterialTheme.colorScheme.onPrimary
//            )
//            )


        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        radialGradient(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.onPrimary
                        )
                    )
                    .padding(16.dp),

                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = data.time.toString(),
                    modifier = Modifier.align(Alignment.End),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_sunnycloudy),
                    contentDescription = "Weather Image",
                    modifier = Modifier.width(200.dp)
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = "${data.temperature} °C", // sa vina de pe viewModel
                    fontSize = 44.sp,
                    color = Color.White
                )
                Spacer(
                    modifier = Modifier.height(32.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    WeatherDataDisplay(
                        value = data.pressure.roundToInt(),
                        unit = "hpa",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDisplay(
                        value = data.humidity.roundToInt(),
                        unit = "%",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDisplay(
                        value = data.windSpeed.roundToInt(),
                        unit = "km/h",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                }
            }

//            }

        }
}


@Preview
@Composable
fun WeatherCardPreview(){
    val data = WeatherData(
        time = LocalDateTime.now().toString(),
        temperature = 25.2,
        pressure = 1000.0,
        humidity = 56.0,
        weatherType = WeatherType.fromWMO(0),
        windSpeed = 12.0
    )
    WeatherCard(data = data)
}

