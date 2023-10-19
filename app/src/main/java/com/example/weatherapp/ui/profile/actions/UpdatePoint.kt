package com.example.weatherapp.ui.profile.actions

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.weatherapp.data.local.weights.Weights
import com.example.weatherapp.ui.profile.ExpandableListViewModel
import com.example.weatherapp.ui.viewModels.PointsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePoint(viewModel: PointsViewModel, expandViewModel: ExpandableListViewModel){
    val list = viewModel.statePoints.collectAsState()
    val context = LocalContext.current
    var count by remember { mutableIntStateOf(0) }
    var enable by remember { mutableStateOf(false) }

    list.value.points?.forEachIndexed {index, it ->

        var text = "${index+1}. "
        text += it.city.ifEmpty { ""+it.latitude + " ; "+ it.longitude }

        val pointId = it.id

        val weights = mutableListOf<String>()
        weights.add(it.accWeight.toString())
        weights.add(it.omWeight.toString())
        weights.add(it.vcWeight.toString())

        val strings = mutableListOf("AccuWeather", "OpenMeteo", "VisualCrossing")
        val icons = mutableListOf(R.drawable.accuweather, R.drawable.open_meteo, R.drawable.visualcrossing__1_)

        var isModified by remember {
            mutableStateOf(false)
        }

        val results  by viewModel.results.collectAsState(initial = null)

        if(results != null){
//        arat
            Toast.makeText(context, results, Toast.LENGTH_SHORT).show()
        }

        
        Row(verticalAlignment = Alignment.CenterVertically,
        ){
            if(isModified)
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "edited icon",
                    tint = Color.White
                )
            Text(
                modifier = Modifier.width(128.dp),
                text = text,
                fontSize = 18.sp,
                color = Color.White

            )

            var accWeight by rememberSaveable {
                mutableStateOf(weights[0])
            }
            var omWeight by rememberSaveable {
                mutableStateOf(weights[1])
            }
            var vcWeight by rememberSaveable {
                mutableStateOf(weights[2])
            }

            LazyRow(
                modifier = Modifier.padding(4.dp)
            ){

                itemsIndexed(weights){ index,_ ->



//                    var updatePoint by remember {
//                        mutableStateOf(Weights(0, "", 1.0, 1.0, 1.0, 1.0, 1.0))
//                    }

                    if(index == 0) {
                        OutlinedTextField(
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color.White,
                                placeholderColor = Color.White.copy(alpha = 0.75f)
                            ),
                            supportingText = { Text("Oarecare", modifier = Modifier.background(Color.White)) },
                            value = accWeight,
                            onValueChange = { itt: String ->

                                accWeight = itt
                                isModified = (itt != weights[index]) || (omWeight != weights[1]) || (vcWeight != weights[2])

                                Log.d("Update point: ", "$accWeight \n$omWeight\n$vcWeight")


                                if(accWeight.isEmpty()) accWeight = "0"

                                if(isModified && itt.isNotEmpty()){
                                    viewModel.addUpdatePoint(Weights(pointId, it.city, accWeight = accWeight.toDouble(), omWeight = omWeight.toDouble(), vcWeight = vcWeight.toDouble(), latitude = it.latitude, longitude = it.longitude ))
                                    count++
                                }
                                else{
                                    viewModel.removeUpdatePoint(Weights(pointId, it.city, accWeight = accWeight.toDouble(), omWeight = omWeight.toDouble(), vcWeight = vcWeight.toDouble(), latitude = it.latitude, longitude = it.longitude ))
                                    count--
                                }


                                //  editableWeights[index] = it.toString()
                                // viewModel.updateCityName(it, context = context)

                            },
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Sentences,
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Decimal
                            ),
                            label = {
                                Text(
                                    text = strings[index],
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            },
                            placeholder = { Text(text = "Weight") },
                            leadingIcon = {
                                Image(
                                    painterResource(id = icons[index]),
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .width(48.dp)
                                        .height(48.dp)
                                        .background(Color.White),
                                    contentDescription = "Search icon"
                                )

                            },
                            modifier = Modifier
                                .padding(8.dp)
                                .height(64.dp),
                            //  isError = state.errors.isNotEmpty()

                        )
                    }
                    if(index == 1){

                        OutlinedTextField(
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color.White,
                                placeholderColor = Color.White.copy(alpha = 0.75f)
                            ),
                            supportingText = { Text("Oarecare", modifier = Modifier.background(Color.White)) },
                            value = omWeight,
                            onValueChange = { itt: String ->

                                omWeight = itt

                                if(omWeight.isEmpty()) omWeight = "0"


                                isModified = (itt != weights[index]) || (accWeight != weights[0]) || (vcWeight != weights[2])

                                Log.d("Update point: ", "$accWeight \n$omWeight\n$vcWeight")



                                if(isModified && itt.isNotEmpty()){
                                    viewModel.addUpdatePoint(Weights(pointId, it.city, accWeight = accWeight.toDouble(), omWeight = omWeight.toDouble(), vcWeight = vcWeight.toDouble(), latitude = it.latitude, longitude = it.longitude ))
                                }


                                //  editableWeights[index] = it.toString()
                                // viewModel.updateCityName(it, context = context)

                            },
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Sentences,
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Decimal
                            ),
                            label = {
                                Text(
                                    text = strings[index],
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            },
                            placeholder = { Text(text = "Weight") },
                            leadingIcon = {
                                Image(
                                    painterResource(id = icons[index]),
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .width(48.dp)
                                        .height(48.dp)
                                        .background(Color.White),
                                    contentDescription = "Search icon"
                                )

                            },
                            modifier = Modifier
                                .padding(8.dp)
                                .height(64.dp),
                            //  isError = state.errors.isNotEmpty()
                        )

                    }
                    if(index == 2){

                        OutlinedTextField(
                            value = vcWeight,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color.White,
                                placeholderColor = Color.White.copy(alpha = 0.75f)
                            ),
                            supportingText = { Text("Oarecare", modifier = Modifier.background(Color.White)) },
                            onValueChange = { itt: String ->

                                vcWeight = itt

                                isModified = (itt != weights[index]) || (omWeight != weights[1]) || (accWeight != weights[0])


                                Log.d("Update point: ", "$accWeight \n$omWeight\n$vcWeight")

                                if(vcWeight.isEmpty()) vcWeight = "0"

                                if(isModified && itt.isNotEmpty()){
                                    viewModel.addUpdatePoint(Weights(pointId, it.city, accWeight = accWeight.toDouble(), omWeight = omWeight.toDouble(), vcWeight = vcWeight.toDouble(), latitude = it.latitude, longitude = it.longitude ))
                                }


                                //  editableWeights[index] = it.toString()
                                // viewModel.updateCityName(it, context = context)

                            },
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Sentences,
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Decimal
                            ),
                            label = {
                                Text(
                                    text = strings[index],
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            },
                            placeholder = { Text(text = "Weight") },
                            leadingIcon = {
                                Image(
                                    painterResource(id = icons[index]),
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .width(48.dp)
                                        .height(48.dp)
//                                        .background(Color.White),
                                        .background(Color.White),
                                    contentDescription = "Search icon"
                                )

                            },
                            modifier = Modifier
                                .padding(8.dp)
                                .height(64.dp),
                            //  isError = state.errors.isNotEmpty()
                        )

                    }
                }
            }
        }
    }
   enable = count > 0
    Button(onClick ={
        val r = viewModel.updatePoints()
        expandViewModel.onItemClicked(2)
        Log.d("Update result: ", r.toString())
//        if(r.isSuccess)
//         Toast.makeText(context, context.getText(R.string.UpdatePointSuccess), Toast.LENGTH_SHORT).show()
//        else Toast.makeText(context, context.getText(R.string.UpdatePointFailure), Toast.LENGTH_SHORT).show()

    },
    enabled = enable
    ){
        Text(text = "Update")
    }
}