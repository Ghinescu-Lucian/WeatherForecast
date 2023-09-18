package com.example.weatherapp.ui.maps

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import androidx.core.graphics.alpha
import com.example.weatherapp.ui.maps.clusters.ZoneClusterItem
import com.example.weatherapp.ui.maps.clusters.ZoneClusterManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.ktx.model.polygonOptions
import kotlin.math.round
import android.graphics.drawable.Icon as Icon1


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
fun Map(modifier: Modifier = Modifier){

    val timisoara = LatLng(45.774489, 21.212534)
    val cameraPositionState = rememberCameraPositionState()
//    {
//        position = CameraPosition.fromLatLngZoom(timisoara, 10f)
//    }


    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {

        val context = LocalContext.current
//        MapEffect(clusterItems) { map ->
////            val clusterManager = ZoneClusterManager(context,map)
////            clusterManager.addItems(clusterItems)
////            map.setOnCameraIdleListener ( clusterManager )
////            map.setOnMarkerClickListener(clusterManager)
//            clusterItems.forEach { clusterItem ->
//                map.addPolygon(clusterItem.polygonOptions)
//            }
//        }

        Marker(
            state = MarkerState(position = timisoara),
            title = "Timisoara",
            snippet = "Timisoara",
            icon = setCustomMapIcon("Timisoara")
        )

        LaunchedEffect(Unit) {
            if (clusterItems.isNotEmpty()) {
                cameraPositionState.animate(
                    update = CameraUpdateFactory.newLatLngBounds(
                        calculateZoneLatLngBounds(),
                        0
                    )
                )
            }
        }
    }
}

fun calculateZoneLatLngBounds(): LatLngBounds {
    // Get all the points from all the polygons and calculate the camera view that will show them all.
    val latLngs = clusterItems.map { it.polygonOptions }
        .map { it.points.map { LatLng(it.latitude, it.longitude) } }.flatten()
    return latLngs.calculateCameraViewPoints().getCenterOfPolygon()
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
