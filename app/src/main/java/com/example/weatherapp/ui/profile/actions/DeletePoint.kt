package com.example.weatherapp.ui.profile.actions

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.data.local.weights.Weights
import com.example.weatherapp.ui.profile.ExpandableListViewModel
import com.example.weatherapp.ui.viewModels.PointsViewModel

@Composable
fun DeletePoint(viewModel: PointsViewModel, expandViewModel: ExpandableListViewModel) {
    val list = viewModel.statePoints.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var accord by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    var count by remember { mutableIntStateOf(0) }
    var enabled by remember { mutableStateOf(false)}

    val results  by viewModel.results.collectAsState(initial = null)

    if(results != null){
//        arat
        Toast.makeText(context, results, Toast.LENGTH_SHORT).show()
    }

    list.value.points?.forEachIndexed { index, it ->

        var text = "${index + 1}. "
        text += it.city.ifEmpty { "" + it.latitude + " ; " + it.longitude }

        val listStrings = mutableListOf<String>()
//                            listStrings.add(text)
        listStrings.add("AccuWeather:    " + it.accWeight.toString())
        listStrings.add("OpenMeteo:      " + it.omWeight.toString())
        listStrings.add("VisualCrossing: " + it.vcWeight.toString())
        val checked = remember { mutableStateOf(false) }
        Row(verticalAlignment = Alignment.CenterVertically) {

            Checkbox(
                checked = checked.value,
                onCheckedChange = {
                        isChecked ->
                        checked.value = isChecked
                        Log.d("Delete point:", "checked")

                        if(isChecked) {
                            viewModel.addDeletePoint(Weights(it.id, "", 1.0, 1.0, 1.0, 1.0, 1.0))
                            count++
                        }
                        else {
                            count--
                            viewModel.removeDeltePoint(Weights(it.id, "", 1.0, 1.0, 1.0, 1.0, 1.0))
                        }
                }
            )

            Text(
                modifier = Modifier.width(128.dp),
                text = text,
                fontSize = 18.sp

            )

            LazyRow(
                modifier = Modifier.padding(4.dp)
            ) {
                itemsIndexed(listStrings) { _, item ->
                    Text(
                        modifier = Modifier.width(128.dp),
                        text = item,
                        fontSize = 16.sp

                    )
                }
            }
        }

    }

    enabled = count > 0

    Button(onClick = {
        showDialog = true
    },
        enabled = enabled
    ) {
        Row(verticalAlignment = Alignment.CenterVertically){
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "delete_icon"
                )
            Text("Delete")
        }

    }



    if(showDialog){
        AlertDialog(onDismissRequest = { showDialog = false },
            text = {
                Text(context.getString(R.string.DeleteAlert))
            },
            confirmButton = {
                 Button(onClick = {
                     accord = true
                     showDialog = false
                     val r = viewModel.deletePoints()
//                     if(r.isSuccess) Toast.makeText(context, context.getText(R.string.DeletePointSucces), Toast.LENGTH_SHORT).show()
//                     else Toast.makeText(context, context.getText(R.string.DeletePointFailure), Toast.LENGTH_SHORT).show()
                     expandViewModel.onItemClicked(3)
                 }){
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showDialog = false
                    accord = false
                }) {
                    Text("No")
                }
            }
        )
    }
}