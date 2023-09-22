package com.example.weatherapp.ui.Profile

import android.content.Context
import android.widget.EditText
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.R
import com.example.weatherapp.ui.Profile.actions.AddNewPoint
import com.example.weatherapp.ui.Profile.actions.DeletePoint
import com.example.weatherapp.ui.Profile.actions.UpdatePoint
import com.example.weatherapp.ui.Profile.actions.YourPoints
import com.example.weatherapp.ui.viewModels.PointsViewModel


@Composable
fun CategorizedLazyColumn( modifier:Modifier   = Modifier, actions: List<Int>, context : Context, viewModelExpandable: ExpandableListViewModel = hiltViewModel(),
                           viewModelPoints: PointsViewModel = hiltViewModel()){

    val itemsIds by viewModelExpandable.itemIds.collectAsState()
    //val itemsIds1 by rememberSaveable { mutableStateOf(mutableListOf<Int>()) } Rotatie de ecrane
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ){
        itemsIndexed(actions){index, item ->
            ExpandableContainerView(text = context.getString(item), onClickItem = {viewModelExpandable.onItemClicked(index)}, expanded = itemsIds.contains(index), viewModel = viewModelPoints, index = index)

        }
    }
}

@Composable
fun ExpandableContainerView(text: String, onClickItem:()->Unit, expanded: Boolean, viewModel: PointsViewModel, index: Int){
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
            HeaderView(text = text , onClickItem= onClickItem, expanded = expanded )
            ExpandableView(text = text, isExpanded = expanded, viewModel = viewModel, index = index)

        }
    }
}
@Composable
fun HeaderView(text: String, onClickItem: () -> Unit, expanded: Boolean) {

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .clickable(
                indication = null, // Removes the ripple effect on tap
                interactionSource = remember { MutableInteractionSource() }, // Removes the ripple effect on tap
                onClick = onClickItem
            )
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.width(256.dp),
            horizontalArrangement = Arrangement.Center,
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
                fontSize = 27.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(256.dp)
                    .padding(8.dp)
            )

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableView(text: String, isExpanded: Boolean, viewModel: PointsViewModel, index: Int) {
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

    AnimatedVisibility(
        visible = isExpanded,
        enter = expandTransition,
        exit = collapseTransition
    ) {
        Box(modifier = Modifier.padding(15.dp)) {
            Column {

                Column {
                    if (index == 0) {
                       YourPoints(viewModel = viewModel, text = text )
                    }

                    if(index == 1){
                      AddNewPoint()
                    }

                    if(index == 2){
                        UpdatePoint()
                        val list = viewModel.statePoints.collectAsState()

                        list.value.points?.forEachIndexed {index, it ->

                            var text = "${index+1}. "
                            if(it.city.isNotEmpty()) text +=  it.city
                            else text += ""+it.latitude + " ; "+ it.longitude

                            val listStrings = mutableListOf<String>()
                            listStrings.add("AccuWeather:    "+it.accWeight.toString())
                            listStrings.add("OpenMeteo:      "+it.omWeight.toString())
                            listStrings.add("VisualCrossing: "+it.vcWeight.toString())
                            Row(){
                                Text(
                                    modifier = Modifier.width(128.dp),
                                    text = "$text",
                                    fontSize = 18.sp

                                )

                                LazyRow(
                                    modifier = Modifier.padding(4.dp)
                                ){
                                    itemsIndexed(listStrings){ index,item ->
                                        OutlinedTextField(
                                            value = item,
                                            onValueChange = { it: String ->

                                                text = it
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
                                    }
                                }
                            }

                        }
                    }
                    if(index == 3){
                        Column {
                            DeletePoint()
                            val list = viewModel.statePoints.collectAsState()

                            list.value.points?.forEachIndexed { index, it ->

                                var text = "${index + 1}. "
                                if (it.city.isNotEmpty()) text += it.city
                                else text += "" + it.latitude + " ; " + it.longitude

                                val listStrings = mutableListOf<String>()
//                            listStrings.add(text)
                                listStrings.add("AccuWeather:    " + it.accWeight.toString())
                                listStrings.add("OpenMeteo:      " + it.omWeight.toString())
                                listStrings.add("VisualCrossing: " + it.vcWeight.toString())
                                val checked = remember { mutableStateOf(false) }
                                Row(verticalAlignment = Alignment.CenterVertically) {

                                    Checkbox(
                                        checked = checked.value,
                                        onCheckedChange = { isChecked -> checked.value = isChecked }
                                    )

                                    Text(
                                        modifier = Modifier.width(128.dp),
                                        text = "$text",
                                        fontSize = 18.sp

                                    )

                                    LazyRow(
                                        modifier = Modifier.padding(4.dp)
                                    ) {
                                        itemsIndexed(listStrings) { index, item ->
                                            Text(
                                                modifier = Modifier.width(128.dp),
                                                text = item,
                                                fontSize = 16.sp

                                            )
                                        }
                                    }
                                }

                            }
                            Button(onClick = { }) {
                                Text("Delete")
                            }
                        }
                    }

                }
            }
        }
    }
}


@Preview
@Composable
fun HeaderViewPreview() {
    HeaderView("Question ceva", {}, true)
}

@Preview(showBackground = true)
@Composable
fun ExpandableContainerViewPreview() {
    val v : PointsViewModel = hiltViewModel()
    ExpandableContainerView(
      text = "Ceva",
        onClickItem = {},
        expanded = true,
        viewModel = v,
        index = 1
    )
}


