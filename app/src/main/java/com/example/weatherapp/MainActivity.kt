package com.example.weatherapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.domain.weather.Interactors.WeatherDataInteractorImpl
import com.example.weatherapp.ui.WeatherCard
import com.example.weatherapp.ui.WeatherForecast
import com.example.weatherapp.ui.WeatherState
import com.example.weatherapp.ui.WeatherViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {

//    private val viewModel : WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val interactor = WeatherDataInteractorImpl()
        val viewModel = WeatherViewModel(interactor = interactor)

        setContent {
            val state by viewModel.state.collectAsState()
                WeatherApp(state = state )
        }
    }
}

@Composable
fun WeatherApp(
    state: WeatherState
){



    WeatherAppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background( MaterialTheme.colorScheme.primary
                )

        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                WeatherCard(
                   data = state.weatherInfo?.currentWeatherData,
                    backgroundColor = MaterialTheme.colorScheme.primary
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )


//                viewModel.loadWeatherInfo()
//                val v = viewModel.state.weatherInfo?.weatherDataPerDay?.get(1)
//                Log.d("Luky", v.toString())
                WeatherForecast(state = state)
            }
            if(state.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            state.error?.let{errorMessage ->
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}


@Preview
@Composable
fun AppPreview(){

    val interactor = WeatherDataInteractorImpl()
    val viewModel = WeatherViewModel(interactor = interactor)
    val state = viewModel.state.collectAsState()
    WeatherAppTheme {
        WeatherApp(state = state.value )
    }

}

