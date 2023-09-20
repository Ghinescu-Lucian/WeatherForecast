package com.example.weatherapp.ui.maps

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.data.local.weights.Weights
import com.example.weatherapp.ui.maps.clusters.ZoneClusterItem
import com.example.weatherapp.ui.viewModels.ProfileViewModel
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.ktx.model.cameraPosition
import com.google.maps.android.ktx.model.polygonOptions


val fill_color = ColorUtils.setAlphaComponent(Color.RED, 150)

val clusterItems = listOf(
    ZoneClusterItem(
        id = "zone-1",
        title = "Zone 1",
        snippet = "This is Zone 1.",
        polygonOptions = polygonOptions {
            add(LatLng(45.774489, 21.212534))
            add(LatLng(45.774489, 22.212534))
            add(LatLng(45.774489, 20.212534))
            add(LatLng(44.774489, 21.212534))
            add(LatLng(46.774489, 21.212534))
            fillColor(fill_color)
        }
    ),
    ZoneClusterItem(
        id = "zone-2",
        title = "Zone 2",
        snippet = "This is Zone 2.",
        polygonOptions = polygonOptions {
            add(LatLng(49.110, -122.554))
            add(LatLng(49.107, -122.559))
            add(LatLng(49.103, -122.551))
            add(LatLng(49.112, -122.549))
            fillColor(fill_color)
        }
    )
)


@Composable
 fun Map(modifier: Modifier = Modifier,
                points: List<Weights>,
                viewModel: ProfileViewModel = hiltViewModel()
        ){

    var markers by remember { mutableStateOf(listOf<LatLng>()) }



    var state  = viewModel.state.collectAsState()
    var alreadyPoints = state.value.points
//     val timisoara = LatLng(45.774489, 21.212534)

//    Calculate camera view
    var firstPoint = Weights(0, "",1.0, 1.0, 1.0, 49.774489, 21.212534)
    val cameraPositionState = rememberCameraPositionState(){
        Log.d("Points cevawq ", firstPoint.latitude.toString())
        position = CameraPosition.fromLatLngZoom(LatLng(firstPoint.latitude, firstPoint.longitude), 5f)
    }
    alreadyPoints?.forEachIndexed{ index, it ->
        Log.d("Points ceva","ceva"+ index+ " "+it.latitude)
        if(index == 0) {
            val center = alreadyPoints?.let { calculateZoneLatLngBounds(it) }?.center
            cameraPositionState.position = CameraPosition.fromLatLngZoom(LatLng(center!!.latitude, center.longitude), 4f )
//         cameraPositionState.position = CameraPosition.fromLatLngZoom(LatLng(it.latitude, it.longitude), 5f)
        }
    }

    Log.d("Points ceva ", firstPoint.latitude.toString())



    var mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(mapToolbarEnabled = false, compassEnabled = true, rotationGesturesEnabled = true, myLocationButtonEnabled = true)
        )
    }


//    LaunchedEffect(Unit) {
//        alreadyPoints = viewModel.getAllPoints()
//        Log.d("points1", alreadyPoints.toString())
////        if (clusterItems.isNotEmpty()) {
////            cameraPositionState.animate(
////                update = CameraUpdateFactory.newLatLngBounds(
////                    calculateZoneLatLngBounds(),
////                    0
////                )
////            )
////        }
//    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings,
        onMapClick = {
//            markers.add(it)
           markers = markers + it
            Log.d("Map", ""+it.latitude+" , "+it.longitude)
        }
    ) {




        markers.forEach{
            Marker(
                state = MarkerState(position = it),
                onClick = { itt ->
                    markers = markers.minus(it)
                     true
                }
            )
        }

//        Log.d("pointss", alreadyPoints?.size.toString())
//
        alreadyPoints?.forEach {
//            Log.d("Points", "from db"+ it.latitude+ " "+ it.longitude)
            Marker(
                state = MarkerState(position = LatLng(it.latitude, it.longitude)),
                icon = setCustomMapIcon(it.city),
                title = it.city
                )
        }





    }
}

fun calculateZoneLatLngBounds(points: List<Weights>): LatLngBounds {

    var listLatLong = mutableListOf<LatLng>()

    points.forEach{
        val latLng = LatLng(it.latitude, it.longitude)
        listLatLong += latLng
    }
    return listLatLong.calculateCameraViewPoints().getCenterOfPolygon()

}
private fun setCustomMapIcon(message: String): BitmapDescriptor {

    val paintBlackFill = Paint().apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        isAntiAlias = true
        color = Color.DKGRAY
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 30.dp.value
    }

    val paintTextWhite = Paint().apply {
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        isAntiAlias = true
        color = Color.WHITE
        textAlign = Paint.Align.CENTER
        strokeWidth = 6.dp.value
        textSize = 48.dp.value
    }

    val paintWhite = Paint().apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        isAntiAlias = true
        color = Color.WHITE
        strokeWidth = 6.dp.value
    }

    val height = 150f
    val widthPadding = 80.dp.value
    val width = paintTextWhite.measureText(message, 0, message.length) + widthPadding
    val roundStart = height/3
    val path = Path().apply {
        arcTo(0f, 0f,
            roundStart * 2, roundStart * 2,
            -90f, -180f, true)
        lineTo(width/2 - roundStart / 2, height * 2/3)
        lineTo(width/2, height)
        lineTo(width/2 + roundStart / 2, height * 2/3)
        lineTo(width - roundStart, height * 2/3)
        arcTo(width - roundStart * 2, 0f,
            width, height * 2/3,
            90f, -180f, true)
        lineTo(roundStart, 0f)
    }

    val bm = Bitmap.createBitmap(width.toInt(), height.toInt(), Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bm)
    canvas.drawPath(path, paintBlackFill)
    canvas.drawPath(path, paintWhite)
    canvas.drawText(message, width/2, height * 2/3 * 2/3, paintTextWhite)
    return BitmapDescriptorFactory.fromBitmap(bm)
}
