package com.example.weatherapp.ui.dailyForecasts

import android.graphics.PointF
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun DailyGraph(modifier : Modifier = Modifier,
                data: List<List<Float>>
               ){

    val animationProgress = remember{
        Animatable(0f)
    }
    LaunchedEffect(key1 = data, block = {
        animationProgress.animateTo(1f, tween(3000 ))
    })

    Box(
        modifier = modifier
            .flipped()
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
     ) {


        Spacer(
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp)
                .aspectRatio(3 / 2f)
                .align(Alignment.Center)
//                .horizontalScroll(rememberScrollState())
//                .fillMaxHeight()
//                .width(600.dp)
                .fillMaxSize()
                .drawWithCache {

                    val brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Green.copy(alpha = 0.75f),

                            )
                    )

                    onDrawBehind {

                        val barWidthPx = 1.dp.toPx()
                        drawRect(Color.Cyan, style = Stroke(barWidthPx))

//                    Draw grid

                        val verticalLines = 3
                        val verticalSize = size.width / (verticalLines + 1)

                        repeat(verticalLines) { i ->
                            val startX = verticalSize * (i + 1)
                            drawLine(
                                Color.Cyan,
                                start = Offset(startX, 0f),
                                end = Offset(startX, size.height),
                                strokeWidth = barWidthPx
                            )

                        }

                        val horizontalLines = 5
                        val sectionSize = size.height / (horizontalLines + 1)
                        repeat(horizontalLines) { i ->
                            val startY = sectionSize * (i + 1)
                            drawLine(
                                Color.Cyan,
                                start = Offset(0f, startY),
                                end = Offset(size.width, startY),
                                strokeWidth = barWidthPx
                            )
                        }

//
                        val minimum = data.minWith(Comparator.comparing { it[1] })
                        val maximum = data.maxWith(Comparator.comparing { it[1] })
                        val diff = maximum[1] - minimum[1]

                        val unitY = size.height / diff
                        val unitX = size.width / (data.size - 1)
//                        val newData = mutableListOf<List<Float>>()
//
//                        //    Resize the temperatures interval( squeeze by  16.7 % )
//
//                        for(i in data){
//                            val temperature = minimum[1]*1.167f + ( (0.834f * maximum[1] - 1.167f * minimum[1]) / (maximum[1] - minimum[1]) ) * ( i[1] - minimum[1])
//                            val item = listOf(i[0], temperature )
//                            newData.add(item)
//                            Log.d("Graph", "${item[0]} - ${item[1]}\n Dif = ${i[1]/item[1]}")
//                        }


                        val path = generatePath(size, data)
                        val filledPath = Path()
                        filledPath.addPath(path = path)
                        filledPath.lineTo(size.width, size.height)
                        filledPath.lineTo(size.width, 0f)
                        filledPath.close()

//                        Draw path
                        drawPath(
                            path, Color.Green,
                            style = Stroke(
                                width = 2.5.dp.toPx(),
                                cap = StrokeCap.Round
                            )
                        )
                        drawPath(
                            filledPath,
                            brush = brush,
                            style = Fill

                        )
//                        Draw points
                        for (i in data) {
                            drawCircle(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.White,
                                        Color.Yellow.copy(alpha = 0.75f)
                                    )
                                ),
                                radius = 20f,
                                center = Offset(i[0] * unitX, (i[1] - minimum[1]) * unitY),


                                )
                        }
                        for (i in data) {
                            drawCircle(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.LightGray,
                                        Color.Yellow
                                    )
                                ),
                                radius = 20f,
                                center = Offset(i[0] * unitX, (i[1] - minimum[1]) * unitY),
                                style = Stroke(width = 3.dp.toPx())

                            )
                        }
                    }
                }
        )

    }
}


fun generatePath( size: Size, data : List<List<Float>>): Path {
    val path = Path()
    val minimum = data.minWith(Comparator.comparing { it[1] })
    val maximum = data.maxWith(Comparator.comparing { it[1] })
    val diff = maximum[1] - minimum[1]

    val unitY  = size.height / diff
    val unitX = size.width / (data.size-1)

// Control points

//    val controlPoints1 = mutableListOf<PointF>(PointF(data[0][0]*unitX,data[0][1]*unitY))
//    val controlPoints2 = mutableListOf<PointF>(PointF(data[0][0]*unitX,data[0][1]*unitY))
//    for( i in 1 until data.size){
//        controlPoints1.add(PointF((data[i][0] + data[i - 1][0]) / 2, data[i - 1][1]))
//        controlPoints2.add(PointF((data[i][0] + data[i - 1][0]) / 2, data[i][1]))
//
//    }


    data.forEachIndexed { i, item ->
        val x = item[0]*unitX
        val y = (item[1]- minimum[1])*unitY

        val controlPoint1 = PointF(x*1.01f, y*1.1f)
        val controlPoint2 = PointF(x*0.99f, y*0.9f)

        path.cubicTo(
            controlPoint1.x, controlPoint1.y,
            controlPoint2.x, controlPoint2.y,
            x, y
        )
    }
    return path
}

class FlippedModifier : DrawModifier {
    override fun ContentDrawScope.draw() {
        scale(1f, -1f) {
            this@draw.drawContent()
        }
    }
}
fun Modifier.flipped() = this.then(FlippedModifier())

@Composable
@Preview
fun DailyGraphPreview(){
    
    val points = listOf(
        listOf(0f,20f),
        listOf(1f, 23f),
        listOf(2f, 24f),
        listOf(3f, 25f),
        listOf(4f, 23f)
        )
    WeatherAppTheme {
        DailyGraph(data = points)
    }
}


