package com.example.weatherapp.ui.profile.actions

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.profile.ExpandableListViewModel
import com.example.weatherapp.ui.viewModels.PointsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewPoint(navigate : () -> Unit = {}, viewModel: PointsViewModel, expandViewModel: ExpandableListViewModel){



    var accWeight by rememberSaveable {
        mutableStateOf("1")
    }
    var omWeight by rememberSaveable {
        mutableStateOf("1")
    }
    var vcWeight by rememberSaveable {
        mutableStateOf("1")
    }

//    val state = viewModel.statePoints.collectAsState()


    Log.d("Point J", viewModel.point.getPoint().toString() )

    Column(verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Row(verticalAlignment = Alignment.CenterVertically) {

            FloatingActionButton(
                onClick = navigate,
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
            if(viewModel.point.getPoint().city != "")
             Icon(imageVector = Icons.Default.Check, tint = Color.Green, contentDescription ="checked icon" , modifier = Modifier.padding(16.dp))
        }
        OutlinedTextField(
            value = accWeight,
            onValueChange = { it: String ->

                accWeight = it
                // viewModel.updateCityName(it, context = context)

            },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, imeAction = ImeAction.Next, keyboardType = KeyboardType.Decimal),
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
            value = omWeight,
            onValueChange = { it: String ->

                omWeight = it
                // viewModel.updateCityName(it, context = context)

            },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, imeAction = ImeAction.Next, keyboardType = KeyboardType.Decimal),
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
            value = vcWeight,
            onValueChange = { it: String ->

                vcWeight = it
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
        val context = LocalContext.current
        FloatingActionButton(
            onClick = {
                val p = viewModel.point.getPoint()
//              sa fie in viewModel si sa fie string ( in viewModel fac conversia la double
                val newPoint = p.copy(accWeight = accWeight.toDouble(), vcWeight = vcWeight.toDouble(), omWeight = omWeight.toDouble() )

//                Log.d("Add Point:", viewModel.addPoint(newPoint).toString())
//                Log.d("Add point: ", viewModel.statePoints.value.points.toString())

                val r = viewModel.addPoint(newPoint)

               // isExpanded = r.isFailure

                if(r.isSuccess) {
                    Toast.makeText(
                        context,
                        context.getText(R.string.PointInsSucces),
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.point.isExp = false
                }
                else {
                    Toast.makeText(
                        context,
                        context.getText(R.string.PointInsFailure),
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.point.isExp = true
                }
                expandViewModel.onItemClicked(1)
                viewModel.point.restore()
            },
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