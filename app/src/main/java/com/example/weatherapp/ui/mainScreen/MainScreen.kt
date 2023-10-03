package com.example.weatherapp.ui.mainScreen

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.MainActivity
import com.example.weatherapp.ui.states.WeatherState

@Composable
fun MainScreen( modifier : Modifier,
                state : WeatherState,
                online: Boolean = false,
                onRefresh:() -> Unit = {}
){

    val activity = LocalContext.current as? Activity
    val context = LocalContext.current

    Column {

        if(!state.online){
            Text("Offline mode", textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .background(Color(0xFFFD3D00).copy(alpha = 0.6f)),
                fontSize = 21.sp,
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
            } else if (state.error != null) {
                if (state.error.isEmpty()) {
                    if (!online) {
                        Row(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(top = 16.dp, start = 16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Offline", fontSize = 21.sp,
                                color = Color.Black,
                            )
                            Button(
                                onClick = {

                                    val intent = Intent(context, MainActivity::class.java)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    context.startActivity(intent)
                                    activity?.finish()
                                },
                                colors = ButtonDefaults.buttonColors(Color.Transparent),
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    tint = Color.White,
                                    contentDescription = "Refresh"
                                )
                            }
                        }
                    } else {
                        Button(
                            onClick = onRefresh,
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(top = 18.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                tint = Color.White,
                                contentDescription = "Refresh"
                            )
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
}