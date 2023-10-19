package com.example.weatherapp.ui.profile

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.ui.profile.actions.AddNewPoint
import com.example.weatherapp.ui.profile.actions.DeletePoint
import com.example.weatherapp.ui.profile.actions.UpdatePoint
import com.example.weatherapp.ui.profile.actions.YourPoints
import com.example.weatherapp.ui.viewModels.PointsViewModel
import com.example.weatherapp.ui.viewModels.WeatherViewModel


@Composable
fun CategorizedLazyColumn( modifier:Modifier   = Modifier, actions: List<Int>, context : Context, viewModelExpandable: ExpandableListViewModel = hiltViewModel(),
                           viewModelPoints: PointsViewModel ,
                           navigate: () -> Unit = {},
                           online: Boolean?
                             ){

    val itemsIds by viewModelExpandable.itemIds.collectAsState()

    //val itemsIds1 by rememberSaveable { mutableStateOf(mutableListOf<Int>()) } Rotatie de ecrane
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            itemsIndexed(actions) { index, item ->
                ExpandableContainerView(
                    text = context.getString(item),
                    onClickItem = { viewModelExpandable.onItemClicked(index) },
                    expanded = itemsIds.contains(index),
                    viewModel = viewModelPoints,
                    index = index,
                    navigate = navigate,
                    expandViewModel = viewModelExpandable,
                   enabled = index!=1 || online == true

                )

            }
        }
    }
}

@Composable
fun ExpandableContainerView(text: String, onClickItem:()->Unit, expanded: Boolean, viewModel: PointsViewModel, index: Int, navigate: () -> Unit = {}, expandViewModel: ExpandableListViewModel,enabled: Boolean ){
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
            HeaderView(text = text , onClickItem= onClickItem, expanded = expanded, enabled=enabled )
            ExpandableView(text = text, isExpanded = expanded, viewModel = viewModel, index = index, navigate = navigate, expandViewModel = expandViewModel)

        }
    }
}
@Composable
fun HeaderView(text: String, onClickItem: () -> Unit, expanded: Boolean, enabled : Boolean = true) {

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .clickable(
                indication = null, // Removes the ripple effect on tap
                interactionSource = remember { MutableInteractionSource() }, // Removes the ripple effect on tap
                onClick = {
                    if (enabled)
                        onClickItem()
                }
            )
            .padding(8.dp)
    ) {
        Row(
//            modifier = Modifier.width(256.dp),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
           if(expanded)
            Icon(
               imageVector = Icons.Default.KeyboardArrowDown,
//                tint = MaterialTheme.colorScheme.onSurface,
                tint = Color.White,
                contentDescription="",
                modifier = Modifier.padding(start = 8.dp)
            )
            else Icon(
               imageVector = Icons.Default.KeyboardArrowRight,
//               tint = MaterialTheme.colorScheme.onSurface,
               tint = Color.White,
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

@Composable
fun ExpandableView(text: String, isExpanded: Boolean, viewModel: PointsViewModel, index: Int, navigate : () -> Unit = {}, expandViewModel: ExpandableListViewModel) {
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
                       YourPoints(viewModel = viewModel, text = text  )
                    }

                    if(index == 1){
                      AddNewPoint(navigate = navigate, viewModel = viewModel, expandViewModel = expandViewModel)
                    }

                    if(index == 2){
                        UpdatePoint(viewModel = viewModel, expandViewModel = expandViewModel)

                    }
                    if(index == 3){
                        Column {
                            DeletePoint(viewModel = viewModel, expandViewModel = expandViewModel)

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
    val v1: ExpandableListViewModel = hiltViewModel()
    ExpandableContainerView(
      text = "Ceva",
        onClickItem = {},
        expanded = true,
        viewModel = v,
        index = 1,
        expandViewModel = v1,
        enabled = true

    )
}


