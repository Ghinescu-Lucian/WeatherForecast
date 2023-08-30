package com.example.weatherapp


import android.Manifest
import android.content.Context
import android.content.res.Resources
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import com.example.weatherapp.ui.mainScreen.WeatherCard
import com.example.weatherapp.ui.mainScreen.WeatherForecast
import com.example.weatherapp.ui.menu.MenuItem
import com.example.weatherapp.ui.menu.Search
import com.example.weatherapp.ui.menu.menuItems
import com.example.weatherapp.ui.states.WeatherState
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.viewModels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {


    private lateinit var viewModel : WeatherViewModel
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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
            WeatherApp(state = state, this )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherApp(
    state: WeatherState,
    context: Context?
){


    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }



    WeatherAppTheme {

            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                bottomBar = {
                    Box(
                        modifier = Modifier.height(135.dp)
                    ) {

                         NavigationBar(
                            modifier = Modifier.align(Alignment.Center)
                        ) {
                            menuItems.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = selectedItemIndex == index,
                                    onClick = {
                                        selectedItemIndex = index
                                        // use  navigation
//                                   navController.navigate(item.title)
                                    },
                                    label = {
                                        Text(
                                            text =
                                            if (context != null) {
                                                context.getString(item.name)
                                            } else {
                                                Resources.getSystem().getString(item.name)
                                            }
//                                           item.route
//                                         "c"


                                            //  Resources.getSystem().getString(item.name)
                                        )
                                    },
                                    icon = {
                                        BadgedBox(
                                            badge = {

                                            }
                                        ) {
                                            Icon(

                                                imageVector = if (index == selectedItemIndex) {
                                                    ImageVector.vectorResource(id = item.selectedIcon)
                                                } else ImageVector.vectorResource(id = item.icon),
                                                contentDescription = item.route
                                            )
                                        }
                                    }
                                )
                            }
                        }
                        MenuItem(icon = ImageVector.vectorResource(Search.icon),
                            modifier = Modifier.align(Alignment.TopCenter))

                    }
                }

            ) {
                    it->


                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .background(
                            MaterialTheme.colorScheme.primary
                        )


                ) {

                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
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
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.align(Alignment.Center)

                        )
                    }
                    state.error?.let { errorMessage ->
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            textAlign = TextAlign.Center
                        )
                    }

                }
            }

    }

}


@Preview
@Composable
fun AppPreview(){


    val state = WeatherState(
        cityName = "Timisoara",
        weatherInfo = WeatherInfo(
            currentWeatherData = WeatherData(
                time = LocalDateTime.now(),
                temperatureCelsius = 25.2,
                pressure = 1000.0,
                humidity = 56.0,
                weatherType = WeatherType.fromWMO(0),
                windSpeed = 12.0
            ),
            weatherDataPerDay = listOf(
                WeatherDataPerDay(
                    day =0,
                    listOf(
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperatureCelsius = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(0),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperatureCelsius = 25.12,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(1),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperatureCelsius = 25.12,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(56),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperatureCelsius = 25.12,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(75),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperatureCelsius = 25.12,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(45),
                            windSpeed = 12.0
                        )

                    )
                ),
                WeatherDataPerDay(
                    day =1,
                    listOf(
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperatureCelsius = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(2),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperatureCelsius = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(3),
                            windSpeed = 12.0
                        )

                    )
                ),
                WeatherDataPerDay(
                    day =2,
                    listOf(
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperatureCelsius = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(45),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperatureCelsius = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(0),
                            windSpeed = 12.0
                        )

                    )
                ),
                WeatherDataPerDay(
                    day =3,
                    listOf(
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperatureCelsius = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(0),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperatureCelsius = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(0),
                            windSpeed = 12.0
                        )

                    )
                ),
                WeatherDataPerDay(
                    day =4,
                    listOf(
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperatureCelsius = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(0),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperatureCelsius = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(0),
                            windSpeed = 12.0
                        )

                    )
                ),
            )
        )
    )
    WeatherAppTheme {
        val x = Resources.getSystem()
        WeatherApp(state = state, context = null)
    }

}




