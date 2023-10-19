package com.example.weatherapp.ui.mainScreen

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.ui.states.WeatherState
import java.time.LocalDateTime

@Composable
fun MainScreen( modifier : Modifier,
                state : WeatherState,
                online: Boolean?,
                onRefresh:() -> Unit = {},
                timeViewModel: MainScreenViewModel = hiltViewModel()
) {

    val activity = LocalContext.current as? Activity
    val context = LocalContext.current
    var cityName = "Unknown"
    if(!state.cityName.isNullOrEmpty()) cityName = state.cityName
    Log.d("StateSearch1:", state.online.toString())

    Column {

        if (state.online != null && !state.online) {
            Text(
                "Offline mode", textAlign = TextAlign.Center,
                modifier = Modifier

                    .fillMaxWidth()
                    .background(Color(0xFFFD0000).copy(alpha = 0.95f)),
                fontSize = 14.sp,
                color = Color.White
//                style = TextStyle(background = Color.Red)
            )
        }
        Box(
            modifier = modifier
                .fillMaxSize()
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
                    data = state.weatherInfo?.currentWeatherData, city = cityName
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
            } else if (state.error != null) {
                if (state.error.isEmpty()) {
//                    if (state.online!= null && !online) {
//                        Row(
//                            modifier = Modifier
//                                .align(Alignment.TopStart)
//                                .padding(top = 16.dp, start = 16.dp),
//                            horizontalArrangement = Arrangement.Center,
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Text(
//                                "Offline", fontSize = 21.sp,
//                                color = Color.Black,
//                            )
//                            Button(
//                                onClick = {
//
//                                    val intent = Intent(context, MainActivity::class.java)
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                                    context.startActivity(intent)
//                                    activity?.finish()
//                                },
//                                colors = ButtonDefaults.buttonColors(Color.Transparent),
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Default.Refresh,
//                                    tint = Color.White,
//                                    contentDescription = "Refresh"
//                                )
//                            }
//                        }
//                    } else {
                    Button(
                        onClick = onRefresh,
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(top = 18.dp)
                    ) {



                            Log.d("Time:",
                                LocalDateTime.now()
                                    .toString() + " " + state.weatherInfo?.currentWeatherData?.time
                            )
//                                if (state.weatherInfo?.currentWeatherData?.time != LocalDateTime.now().toString()
//                                )




                            if (state.online == null || state.online) {

                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    tint = Color.White,
                                    contentDescription = "Refresh"
                                )

                                if (timeViewModel.isTimeOut(state.weatherInfo?.currentWeatherData!!.time)) {
                                    Text(
                                        "Old data, please refresh", fontSize = 10.sp,
                                        color = Color.White,
                                        fontStyle = FontStyle.Italic,
                                        modifier = Modifier.padding(start = 8.dp)

                                    )
                                }


                        }
                    }
                }
            }
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
