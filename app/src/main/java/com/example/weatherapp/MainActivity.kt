package com.example.weatherapp


import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.data.remote.accuWeather.AccuWeatherApi
import com.example.weatherapp.data.remote.accuWeather.RetrofitHelperAccuWeather
import com.example.weatherapp.data.remote.accuWeather.repository.AccuWeatherRepositoryImpl
import com.example.weatherapp.ui.mainScreen.WeatherCard
import com.example.weatherapp.ui.mainScreen.WeatherForecast
import com.example.weatherapp.ui.states.WeatherState
import com.example.weatherapp.ui.viewModels.WeatherViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private lateinit var viewModel : WeatherViewModel
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherApi = RetrofitHelperAccuWeather.getInstance().create(AccuWeatherApi::class.java)
        val repository = AccuWeatherRepositoryImpl(api =weatherApi )

        GlobalScope.launch {

            val r  = repository.getHourlyWeatherData(0.0,0.0)
            r.onSuccess {
                Log.d("AccuWeather",it.weatherDataPerDay.size.toString())
            }

        }


//        val visualCrossingApi = RetrofitHelperVisual_Crossing.getInstance().create(VisualCrossingApi::class.java)
//        val repository = VisualCrossingRepositoryImpl(visualCrossingApi)
//        GlobalScope.launch{
////            val r = visualCrossingApi.getW
//            val result = repository.getWeatherData(45.189818, 22.351459).onSuccess { it ->
//                Log.d("VisualCrossing",it.weatherDataPerDay.toString())
//            }
////
//                    Log.d("VisualCrossing", result.toString())
//        }

//        val weatherApi = RetrofitHelper.getInstance().create(WeatherApi::class.java)
//        val interactor = WeatherDataInteractorImpl()
//        val viewModel = WeatherViewModel(
//            interactor = interactor,
//        )


//        Ok

//       GlobalScope.launch {
//           val result = viewModel.repository.getWeatherData(45.7537, 21.2257);
//           result.onSuccess {
//               Log.d("Retrofit", it.currentWeatherData?.time.toString())
//           }
//        }




//        GlobalScope.launch {
//           val result =  viewModel.locationTracker.getCurrentLocation()
//            Log.d("LocationTracker", result.toString())
////            val toast = Toast.makeText(this, result.toString(), Toast.LENGTH_LONG) // in Activity
////            toast.show()
////            println("CEVA")
//
//        }


        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()){
            //  viewModel.loadWeatherInfo()
        }
////
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))


        setContent {
            viewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            Log.d("State", state.weatherInfo.toString())
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
                .background(
                    MaterialTheme.colorScheme.primary
                )

        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                WeatherCard(
                   data = state.weatherInfo?.currentWeatherData,
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                WeatherForecast(state = state)
            }
            if(state.isLoading){
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.surfaceVariant,
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

//    val interactor = WeatherDataInteractorImpl()
//   // val viewModel = WeatherViewModel(interactor = interactor, repository = , locationTracker = )
//        // val state = viewModel.state.collectAsState()
//    WeatherAppTheme {
//        WeatherApp(state = state.value )
//    }

}




