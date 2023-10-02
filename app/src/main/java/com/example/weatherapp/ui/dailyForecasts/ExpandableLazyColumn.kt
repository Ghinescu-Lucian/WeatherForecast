package com.example.weatherapp.ui.dailyForecasts

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.R
import com.example.weatherapp.domain.weather.WeatherDataPerDay
import com.example.weatherapp.ui.profile.ExpandableListViewModel
import com.example.weatherapp.ui.states.WeatherState
import com.example.weatherapp.ui.viewModels.DailyViewModel
import com.example.weatherapp.ui.viewModels.PointsViewModel


@Composable
fun ExpandableLazyColumn(modifier: Modifier = Modifier,
                         state: WeatherState,
                         viewModel: DailyViewModel,
                         viewModelExpandable: ExpandableListViewModel = hiltViewModel()
){

    val days: List<WeatherDataPerDay> = state.weatherInfo?.weatherDataPerDays!!
    val daysStrings = viewModel.getDaysStrings(state, literal = true)

    Log.d("Days:", days.toString())


    val itemsIds by viewModelExpandable.itemIds.collectAsState()

    //val itemsIds1 by rememberSaveable { mutableStateOf(mutableListOf<Int>()) } Rotatie de ecrane
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ){
        itemsIndexed(days){index, item ->
            ExpandableContainerView(text =daysStrings[index] ,day =item,  onClickItem = {viewModelExpandable.onItemClicked(index) }, expanded = itemsIds.contains(index), viewModel = hiltViewModel(), index = index, expandViewModel = hiltViewModel() )

        }
    }
}

@Composable
fun ExpandableContainerView(text: String, day: WeatherDataPerDay, onClickItem:()->Unit, expanded: Boolean, viewModel: PointsViewModel, index: Int, expandViewModel: ExpandableListViewModel){
    Box(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primary)
    ){
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            HeaderView1(text = text, day = day, onClickItem= onClickItem, expanded = expanded )
            ExpandableView1(text = day.day.toString(),day = day, isExpanded = expanded, viewModel = viewModel, index = index, expandViewModel = expandViewModel)


        }
    }
}
@Composable
fun HeaderView1(text:String, day: WeatherDataPerDay, onClickItem: () -> Unit, expanded: Boolean) {


    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .clickable(
                indication = null, // Removes the ripple effect on tap
                interactionSource = remember { MutableInteractionSource() }, // Removes the ripple effect on tap
                onClick = {
                    Log.d("Clicked", "")
                    onClickItem()

                }
            )
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.width(256.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            if(expanded)
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription="",
                    modifier = Modifier.padding(start = 8.dp)
                )
            else Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription="",
                modifier = Modifier.padding(start = 8.dp)
            )

            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.White,
//                textAlign = TextAlign.Center,
                modifier = Modifier
//                    .width(256.dp)
                    .padding(8.dp)
            )

            if(!expanded)
                Text(
                text = "" + day.maxTemperature+" ºC"
            )
            else Text( text = "          ")
            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(id = day.weatherTypeDay.iconRes),
                contentDescription = ""
            )


        }

    }
}

@Composable
fun ExpandableView1(text: String, day : WeatherDataPerDay, isExpanded: Boolean, viewModel: PointsViewModel, index: Int, expandViewModel: ExpandableListViewModel) {
    // Opening Animation
    val expandTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeIn(
            animationSpec = tween(300)
        )
    }

    // Closing Animation
    val collapseTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeOut(
            animationSpec = tween(300)
        )
    }


//    val formatter = DateTimeFormatter.ofPattern("HH:mm")


    AnimatedVisibility(
        visible = isExpanded,
        enter = expandTransition,
        exit = collapseTransition
    ) {
        Box(modifier = Modifier.padding(15.dp)) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.high_temperature_icon),
                        contentDescription = "",
                        modifier = Modifier.size(48.dp)
                    )
                    Text("Maximum: ")
                    Text(day.maxTemperature.toString() + " ºC")

                }
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.icons8_cold_48),
//                        tint = Color.White,
                        contentDescription = "",
                        modifier = Modifier.size(48.dp)
                    )
                    Text("Minimum: ")
                    Text(day.minTemperature.toString() + " ºC")

                }
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_sunrise),
//                        tint = Color.White,
                        contentDescription = "",
                        modifier = Modifier.size(48.dp)
                    )
                    Text("Sun rise: ")
                    Text(day.sunRise.takeLast(5))
                }
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_sunset),
//                        tint = Color.White,
                        contentDescription = "",
                        modifier = Modifier.size(48.dp)
                    )
                    Text("Sun set: ")
                    Text(day.sunSet.takeLast(5))
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun HeaderViewPreview() {
//    HeaderView("Question ceva", {}, true)
//}

//@Preview(showBackground = true)
//@Composable
//fun ExpandableContainerViewPreview() {
//    val v : PointsViewModel = hiltViewModel()
//    val v1: ExpandableListViewModel = hiltViewModel()
//    ExpandableContainerView(
//        text = "Ceva",
//        onClickItem = {},
//        expanded = true,
//        viewModel = v,
//        index = 1,
//        expandViewModel = v1
//
//    )
//}


