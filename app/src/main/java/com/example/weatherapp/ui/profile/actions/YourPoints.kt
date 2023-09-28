package com.example.weatherapp.ui.profile.actions

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.viewModels.PointsViewModel

@Composable
fun YourPoints(viewModel: PointsViewModel, text: String){
    Text(
        text = text + ":",
        fontSize = 32.sp,
        color = Color.White,
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
    )

    val list = viewModel.statePoints.collectAsState()

    list.value.points?.forEachIndexed {index, it ->

        var text1 = "${index+1}. "
        if(it.city.isNotEmpty()) text1 +=  it.city
        else text1 += ""+it.latitude + " ; "+ it.longitude

        val listStrings = mutableListOf<String>()
//                            listStrings.add(text)
        listStrings.add("AccuWeather:    "+it.accWeight.toString())
        listStrings.add("OpenMeteo:      "+it.omWeight.toString())
        listStrings.add("VisualCrossing: "+it.vcWeight.toString())
        Row(verticalAlignment = Alignment.CenterVertically){
            Text(
                modifier = Modifier.width(128.dp),
                text = text1,
                fontSize = 18.sp

            )

            LazyRow(
                modifier = Modifier.padding(4.dp)
            ){
                itemsIndexed(listStrings){ index,item ->
                    Text(
                        modifier = Modifier.width(128.dp),
                        text = item,
                        fontSize = 16.sp

                    )
                }
            }
        }

    }
}