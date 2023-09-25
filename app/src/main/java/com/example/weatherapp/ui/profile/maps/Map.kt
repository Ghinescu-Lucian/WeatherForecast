package com.example.weatherapp.ui.profile.maps

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
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
import com.example.weatherapp.R
import com.example.weatherapp.Services.geocoder.CitySearch
import com.example.weatherapp.data.local.weights.Weights
import com.example.weatherapp.ui.profile.maps.clusters.ZoneClusterItem
import com.example.weatherapp.ui.viewModels.PointsViewModel
import com.example.weatherapp.ui.viewModels.ProfileViewModel
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.ktx.model.polygonOptions
import kotlin.math.min
import kotlin.math.sqrt


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
                viewModel: ProfileViewModel = hiltViewModel(),
                viewModelPoints : PointsViewModel
        ){

    val context = LocalContext.current


    var markers by remember { mutableStateOf(listOf<LatLng>()) }



    val state  = viewModel.state.collectAsState()
    val alreadyPoints = state.value.points
//     val timisoara = LatLng(45.774489, 21.212534)

//    Calculate camera view
    val firstPoint = Weights(0, "",1.0, 1.0, 1.0, 49.774489, 21.212534)
    val cameraPositionState = rememberCameraPositionState(){
        Log.d("Points cevawq ", firstPoint.latitude.toString())
        position = CameraPosition.fromLatLngZoom(LatLng(firstPoint.latitude, firstPoint.longitude), 5f)
    }
    alreadyPoints?.forEachIndexed{ index, it ->
        Log.d("Points ceva","ceva"+ index+ " "+it.latitude)
        if(index == 0) {
            val center = alreadyPoints.let { calculateZoneLatLngBounds(it) }.center
            val zoom = calculateZoom(alreadyPoints)
            cameraPositionState.position = CameraPosition.fromLatLngZoom(LatLng(center.latitude, center.longitude), zoom )
//         cameraPositionState.position = CameraPosition.fromLatLngZoom(LatLng(it.latitude, it.longitude), 5f)
        }

    }

    Log.d("Points ceva ", firstPoint.latitude.toString())



    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(mapToolbarEnabled = false, compassEnabled = true, rotationGesturesEnabled = true, myLocationButtonEnabled = true)
        )
    }


Column() {
    GoogleMap(
        //modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties =MapProperties(isMyLocationEnabled = true),
        uiSettings = mapUiSettings,
        onMapClick = {
//            markers.add(it)
            if(markers.size == 0) {
                markers = markers + it
                val citySearch = CitySearch()
                val name = citySearch.getCityName(context = context, latitude = it.latitude, longitude = it.longitude)
                Log.d("Point J: ", name)
                viewModelPoints.setNewPoint(Weights(0, name, 1.0, 1.0, 1.0, it.latitude, it.longitude ))
                val statef = viewModel.state.value.curentPoint
                Log.d("Point J", statef.toString())
                viewModelPoints.point.setCity(name)
                viewModelPoints.point.setLatitutde(it.latitude)
                viewModelPoints.point.setLongitude(it.longitude)

            }
            else{
                Toast.makeText( context, context.getText(R.string.MapMessage), Toast.LENGTH_SHORT).show()
            }
            Log.d("Map", "" + it.latitude + " , " + it.longitude)
        }
    ) {


        markers.forEach {

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
               // snippet = "ACC:"+it.accWeight + "\nOM:"+it.omWeight+"\nVC:"+it.vcWeight,
                title = "ACC:"+it.accWeight + "   OM:"+it.omWeight+"   VC:"+it.vcWeight,
            )
        }
    }

}
}

fun calculateZoneLatLngBounds(points: List<Weights>): LatLngBounds {

    val listLatLong = mutableListOf<LatLng>()

    points.forEach{
        val latLng = LatLng(it.latitude, it.longitude)
        listLatLong += latLng
    }
    return listLatLong.calculateCameraViewPoints().getCenterOfPolygon()

}

fun calculateZoom(points: List<Weights>): Float{
    val distances = MutableList<MutableList<Float>>(points.size,init = {i -> MutableList(points.size, init = { i1 -> 0f})})

    points.forEachIndexed{ index , point ->
        points.forEachIndexed{ indx, pnt ->
            if(index == indx)
                distances[index][index]=0f
            else{
                var y1=0.0
                var y2 =0.0
                val dist1 = sqrt(Math.pow(point.latitude - pnt.latitude, 2.0) + Math.pow(point.longitude - pnt.longitude, 2.0))
                if(point.longitude > 0) {
                    y1 = 180-point.longitude
                } else y1 = -180 - point.longitude
                if(pnt.longitude > 0)
                    y2 = 180 - pnt.longitude
                else y2 = -180 - pnt.longitude
                val dist2 = sqrt(Math.pow(point.latitude - pnt.latitude, 2.0) + Math.pow(y1 - y2, 2.0))

                val d_min= min(dist1, dist2)
                distances[index][indx] = d_min.toFloat()

            }
        }
    }

    var d_max = distances[0][0]
    for(i in 0 until distances.size) {
        for(j in 0 until distances[i].size)
            if(distances[i][j] > d_max)
                d_max = distances[i][j]
    }

    var zoom  = 4f
    if(d_max <= 3f)
        zoom = 7f
    else if(d_max <= 5f)
        zoom = 6f
     else if(d_max <= 10f)
        zoom = 5f
    else if (d_max >= 50f)
        zoom = 0f
    Log.d("Calculate zoom: ", zoom.toString() + " " + d_max)
    return zoom

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
