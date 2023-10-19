package com.example.weatherapp.ui.profile

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.profile.maps.Map
import com.example.weatherapp.ui.viewModels.PointsViewModel

@Composable
fun MapScreen(modifier : Modifier = Modifier, navigateBack: () -> Unit = {}, pointsViewModel: PointsViewModel ){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier= Modifier.height(16.dp))
        Text(text = "Place a point on the map")
        Box(modifier = Modifier.fillMaxWidth())
       {
            Button(onClick = { navigateBack() }, modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.TopStart),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", tint = Color.Black)


                }
            }
            Button(onClick = { navigateBack() }, modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.TopCenter)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Done, contentDescription = "done")
                    Text(text = " Done")
                }
            }
        }
        Map( viewModelPoints = pointsViewModel)
    }

}