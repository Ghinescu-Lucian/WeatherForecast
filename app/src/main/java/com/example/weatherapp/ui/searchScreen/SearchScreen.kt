package com.example.weatherapp.ui.searchScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.R
import com.example.weatherapp.Services.geocoder.CitySearch
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.viewModels.SearchViewModel
import com.example.weatherapp.ui.viewModels.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier : Modifier = Modifier,
    viewModel: SearchViewModel,
    viewModelWeahter: WeatherViewModel,
    onClickSearch: () -> Unit = {}

){
    var text by rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val state by remember{ mutableStateOf( viewModel.stateSearch.value)}

    var count by rememberSaveable {
        mutableIntStateOf(0)
    }
    Box(
        modifier = modifier
    ){
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {
            //Text(text = "Under construction Search screen")
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.ic_search_map),
                contentDescription = "Under construction"
            )
            Column {
                OutlinedTextField(
                    value = text,
                    onValueChange = { it: String ->

                        text = it
                        viewModel.updateCityName(it, context = context)

                    },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, imeAction = ImeAction.Done),
                    label = { Text(text = "City search") },
                    placeholder = { Text(text = "Search by city name") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Search icon"
                        )
                    },
                    isError = state.errors.isNotEmpty()
                )
              state.errors.forEach {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = it,
                        color = Color.Red
                    )
                }
            }

            ExtendedFloatingActionButton(
                onClick = {
                   val latLng=  viewModel.getLatLong(context = context)
                    count++
                    Log.d("Search latLng", ""+state.latitude+","+state.longitude)
                    if (state.errors.isEmpty()) {
                        viewModelWeahter.refreshContentSearch(
                            refreshCity = false,
                            cityName = text,
                            lat = latLng.latitude,
                            long = latLng.longitude
                        )
                        onClickSearch()
                    }
                },
                shape = RoundedCornerShape(10.dp),
                text = {Text(text = "Get forecasts")},
                icon = {
                    Icon(Icons.Filled.Search, "search icon")
                },
                containerColor = MaterialTheme.colorScheme.primary


            )
            Text(
                text = count.toString(),
                modifier = Modifier.alpha(0f)
            )

//            Image(
//                painter = painterResource(id = R.drawable.ic_search_map),
//                contentDescription ="" )

            }


         }
}

@Preview
@Composable
fun SearchScreenPreview(){
    WeatherAppTheme {
        SearchScreen(modifier = Modifier, viewModel = SearchViewModel( citySearch = CitySearch()), viewModelWeahter = hiltViewModel())
    }
}
