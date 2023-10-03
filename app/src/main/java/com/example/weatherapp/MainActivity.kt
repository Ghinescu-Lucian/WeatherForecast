package com.example.weatherapp


import android.Manifest
import android.content.Context
import android.content.IntentSender
import android.content.res.Resources
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import com.example.weatherapp.ui.menu.MenuItem
import com.example.weatherapp.ui.menu.Search
import com.example.weatherapp.ui.menu.WeatherNavHost
import com.example.weatherapp.ui.menu.menuItems
import com.example.weatherapp.ui.menu.navigateSingleTopTo
import com.example.weatherapp.ui.networkListener.NetworkListener
import com.example.weatherapp.ui.states.WeatherState
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.viewModels.MainViewModel
import com.example.weatherapp.ui.viewModels.PointsViewModel
import com.example.weatherapp.ui.viewModels.WeatherViewModel
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@AndroidEntryPoint
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



        checkGPSEnabled()

        setContent {

            val networkListener = NetworkListener(Context.CONNECTIVITY_SERVICE, this, viewModel = hiltViewModel())
            networkListener.register()

            val locationManager =   LocalContext.current.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            var offline by rememberSaveable {
                mutableStateOf(false)
            }

            viewModel = hiltViewModel()
                    val points: PointsViewModel = hiltViewModel()
                    val st by points.statePoints.collectAsState()


                    val state by viewModel.state.collectAsState()
                    Log.d("State", state.weatherInfo.toString())



                    WeatherApp(state = state, context = this, viewModel = viewModel)

//            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                if(isOnline(this)) {
//                    viewModel = hiltViewModel()
//                    val points: PointsViewModel = hiltViewModel()
//                    val st by points.statePoints.collectAsState()
//
//
//                    val state by viewModel.state.collectAsState()
//                    Log.d("State", state.weatherInfo.toString())
//
//
//
//                    WeatherApp(state = state, context = this, viewModel = viewModel)
//                    Log.d("Points State", st.toString())
//                }
//                else{
//                    Box(modifier = Modifier.fillMaxSize()) {
//                        Column(
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            modifier = Modifier.align(Alignment.Center)
//                        ){
//                            Text("Please check your internet connection", fontSize = 21.sp)
//                            Spacer(Modifier.size(16.dp))
//                            Text("Offline device", fontSize = 21.sp)
//                            Spacer(Modifier.size(16.dp))
//                            Button(onClick ={
//
//                                val intent = Intent(applicationContext, MainActivity::class.java)
//
//                                // Clear the back stack so that the user cannot navigate back to the previous activities
//                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//
//                                // Start the main activity
//                                startActivity(intent)
//
//                                // Finish this activity
//                                finish()
//
//                            }){
//                                Icon(imageVector = Icons.Default.Refresh, contentDescription ="" )
//                            }
//                            Button(onClick ={
//
//                               offline = true
//
//                            }){
//                                Row() {
//                                    Text("Access offline mode")
//                                    Icon(
//                                        imageVector = Icons.Default.Refresh,
//                                        contentDescription = ""
//                                    )
//                                }
//                            }
//
//                        }
////                        if(offline){
////
////                            val viewModelOff: OfflineViewModel = hiltViewModel()
////
////                            Log.d("Offline view model", viewModelOff::class.simpleName.toString() )
////
////                            val state by viewModelOff.state.collectAsState()
////
////                            WeatherApp(state = state, context = LocalContext.current , viewModel = viewModelOff )
////                        }
////
//                    }
//                }
//            }
//            else{
//                Box(modifier = Modifier.fillMaxSize()) {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier.align(Alignment.Center)
//                ) {
//                    Text("Please enable GPS", fontSize = 21.sp)
//                    Spacer(Modifier.size(16.dp))
//                    Text("GPS is not enabled", fontSize = 21.sp)
//                    Spacer(Modifier.size(16.dp))
//                    Button(onClick ={
//
//                        val intent = Intent(applicationContext, MainActivity::class.java)
//
//                        // Clear the back stack so that the user cannot navigate back to the previous activities
//                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//
//                        // Start the main activity
//                        startActivity(intent)
//
//                        // Finish this activity
//                        finish()
//
//                    }){
//                             Icon(imageVector = Icons.Default.Refresh, contentDescription = "")
//
//
//                    }
//                }
//
//            }
//            }
        }
    }


    private fun checkGPSEnabled() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (!isGPSEnabled) {
            // GPS is not enabled, you can prompt the user to enable it
            requestGPSEnabled()
        }
    }

    private fun requestGPSEnabled() {
        val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener(this, OnSuccessListener<LocationSettingsResponse> { locationSettingsResponse ->
            // GPS is enabled or user agreed to enable it
        })

        task.addOnFailureListener(this, OnFailureListener { exception ->
            when ((exception as? ResolvableApiException)?.statusCode) {
                LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                    // GPS settings are not adequate, show a dialog to the user
                    try {
                        val resolvable = exception as ResolvableApiException
                        resolvable.startResolutionForResult(this, REQUEST_ENABLE_GPS)
                    } catch (sendEx: IntentSender.SendIntentException) {
                        // Ignore the error.
                    }
                }
            }
        })
    }

    companion object {
        private const val REQUEST_ENABLE_GPS = 123
    }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherApp(
    state: WeatherState,
    context: Context,
    viewModel: MainViewModel
){


    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
//    var currentScreen: WeatherDestination by remember {mutableStateOf(Main)}
    val navController = rememberNavController()
//    val currentBackStack  by navController.currentBackStackEntryAsState()
//    val currentDestination = currentBackStack?.destination
//    val curentScreen = menuItems.find{it.route == currentDestination?.route} ?: Main
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    WeatherAppTheme {

            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                snackbarHost = {

                    SnackbarHost(hostState = snackbarHostState)

                               if(!state.online){
                                   LaunchedEffect(key1 = "", block = {
                                       scope.launch {
                                           snackbarHostState.showSnackbar("You are in offline")
                                       }
                                   } )

                                   Log.d("Networkul12", "device offline")
                               }
                    else{
                        Log.d("Networkul12", "device online")

                    }
//                               sa pun intr-un viewModel daca am aplicatia online
                },
//                floatingActionButton = {
//                    ExtendedFloatingActionButton(
//                        text = { Text("Show snackbar") },
//                        icon = { Icon(Icons.Filled.Info, contentDescription = "") },
//                        onClick = {
//                            scope.launch {
//                                snackbarHostState.showSnackbar("Snackbar")
//                            }
//                        }
//                    )
//                },
                bottomBar = {
                    Column(

                    ) {

                        Spacer( modifier = Modifier
                            .height(4.dp)
                            .background(Color.Cyan)
                            .fillMaxWidth()
                        )

                        Box(
                            modifier = Modifier.height(135.dp)
                        ) {
                            NavigationBar(
                                modifier = Modifier.align(Alignment.Center)
                            ) {
                                menuItems.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                                        selected = selectedItemIndex == index,
                                        onClick = {
                                            selectedItemIndex = index
                                            navController.navigateSingleTopTo(item.route)
                                        },
                                        label = {
                                            Text(
                                                text =
                                                context.getString(item.name)
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
                            if(state.online) {
                                MenuItem(icon = ImageVector.vectorResource(Search.icon),
                                    modifier = Modifier.align(Alignment.TopCenter),
                                    onClick = { navController.navigateSingleTopTo(Search.route) }
                                )
                            }
                        }
                    }
                }

            ) {
                    innerPadding ->
//                    MainScreen(modifier = Modifier.padding(it), state = state )
                    WeatherNavHost(navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        context = context,
                        viewModelW = viewModel
                        )

            }

    }

}

fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }
    return false
}




@Preview
@Composable
fun AppPreview(){


    val state = WeatherState(
        cityName = "Timisoara",
        weatherInfo = WeatherInfo(
            currentWeatherData = WeatherData(
                time = LocalDateTime.now().toString().toString(),
                temperature = 25.2,
                pressure = 1000.0,
                humidity = 56.0,
                weatherType = WeatherType.fromWMO(0),
                windSpeed = 12.0
            ),
            weatherDataPerDays = listOf(
                WeatherDataPerDay(
                    day =0,
                    forecasts = listOf(
                        WeatherData(
                            time = LocalDateTime.now().toString(),
                            temperature = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(0),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now().toString(),
                            temperature = 25.12,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(1),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now().toString(),
                            temperature = 25.12,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(56),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now().toString(),
                            temperature = 25.12,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(75),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now().toString(),
                            temperature = 25.12,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(45),
                            windSpeed = 12.0
                        )

                    )
                ),
                WeatherDataPerDay(
                    day =1,
                   forecasts = listOf(
                        WeatherData(
                            time = LocalDateTime.now().toString(),
                            temperature = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(2),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now().toString(),
                            temperature = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(3),
                            windSpeed = 12.0
                        )

                    )
                ),
                WeatherDataPerDay(
                    day =2,
                   forecasts =  listOf(
                        WeatherData(
                            time = LocalDateTime.now().toString(),
                            temperature = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(45),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now().toString(),
                            temperature = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(0),
                            windSpeed = 12.0
                        )

                    )
                ),
                WeatherDataPerDay(
                    day =3,
                    forecasts = listOf(
                        WeatherData(
                            time = LocalDateTime.now().toString(),
                            temperature = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(0),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now().toString(),
                            temperature = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(0),
                            windSpeed = 12.0
                        )

                    )
                ),
                WeatherDataPerDay(
                    day =4,
                  forecasts =   listOf(
                        WeatherData(
                            time = LocalDateTime.now().toString(),
                            temperature = 25.2,
                            pressure = 1000.0,
                            humidity = 56.0,
                            weatherType = WeatherType.fromWMO(0),
                            windSpeed = 12.0
                        ),
                        WeatherData(
                            time = LocalDateTime.now().toString(),
                            temperature = 25.2,
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
    val application =  WeatherApp()// Replace MyApplication with your Application class
    val appContext: Context = application.applicationContext
    val viewModel: WeatherViewModel = hiltViewModel()
    WeatherAppTheme {
        val x = Resources.getSystem()
        WeatherApp(state = state, context = appContext, viewModel = viewModel )
    }

}




