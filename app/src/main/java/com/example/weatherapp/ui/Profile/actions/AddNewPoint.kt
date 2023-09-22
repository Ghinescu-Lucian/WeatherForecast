package com.example.weatherapp.ui.Profile.actions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewPoint(){
    var text1 by rememberSaveable {
        mutableStateOf("")
    }
    var text2 by rememberSaveable {
        mutableStateOf("")
    }
    var text3 by rememberSaveable {
        mutableStateOf("")
    }
    Column(verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        FloatingActionButton(
            onClick = { },
            containerColor = MaterialTheme.colorScheme.onPrimary
        ) {

            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = "point_icon"
                )
                Text(text = "Place a point", fontSize = 16.sp)
            }

        }
        OutlinedTextField(
            value = text1,
            onValueChange = { it: String ->

                text1 = it
                // viewModel.updateCityName(it, context = context)

            },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, imeAction = ImeAction.Done, keyboardType = KeyboardType.Decimal),
            label = { Text(text = "AccuWeather",color = MaterialTheme.colorScheme.onPrimary) },
            placeholder = { Text(text = "Search by city name") },
            leadingIcon = {
                Image(
                    painterResource(id = R.drawable.accuweather),
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .width(48.dp)
                        .height(48.dp)
                        .background(MaterialTheme.colorScheme.onSurface),
                    contentDescription = "Search icon"
                )

            },
            modifier = Modifier
                .padding(8.dp)
                .height(64.dp)
            ,
            //  isError = state.errors.isNotEmpty()
        )
        OutlinedTextField(
            value = text2,
            onValueChange = { it: String ->

                text2 = it
                // viewModel.updateCityName(it, context = context)

            },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, imeAction = ImeAction.Done, keyboardType = KeyboardType.Decimal),
            label = { Text(text = "OpenMeteo",color = MaterialTheme.colorScheme.onPrimary) },
            placeholder = { Text(text = "Search by city name") },
            leadingIcon = {
                Image(
                    painterResource(id = R.drawable.open_meteo),
                    contentDescription = "Search icon",
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .width(48.dp)
                        .height(48.dp)
                        .background(MaterialTheme.colorScheme.onSurface),
                )
            },
            modifier = Modifier
                .padding(8.dp)
                .height(64.dp)
        )
        OutlinedTextField(
            value = text3,
            onValueChange = { it: String ->

                text3 = it
                // viewModel.updateCityName(it, context = context)

            },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, imeAction = ImeAction.Done, keyboardType = KeyboardType.Decimal),
            label = { Text(text = "VisualCrossing",color = MaterialTheme.colorScheme.onPrimary) },
            placeholder = { Text(text = "Search by city name") },
            leadingIcon = {
                Image(
                    painterResource(id = R.drawable.visualcrossing__1_),
                    contentDescription = "Search icon",
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .width(48.dp)
                        .height(48.dp)
                        .background(MaterialTheme.colorScheme.onSurface),

                    )
            },
            modifier = Modifier
                .padding(8.dp)
                .height(64.dp)
        )
        FloatingActionButton(
            onClick = { },
            containerColor = MaterialTheme.colorScheme.onPrimary
        ) {

            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Add", fontSize = 16.sp)
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "point_icon"
                )

            }

        }

    }
}