package com.example.weatherapp.ui.animation

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush

class radialGradient(val colorStart: Color,
                     val colorEnd: Color,

                     ) : ShaderBrush(){
    override fun createShader(size: Size): Shader {
       // val biggerDimension = kotlin.math.abs(size.height * dimenssionFactor)
        val biggerDimension = maxOf(size.height, size.width)
        return RadialGradientShader(
            colors = listOf( colorEnd.copy(0.8f), colorStart.copy(alpha = 0.8f)),
            center = size.center,
            radius = biggerDimension / 2f,
            colorStops = listOf(0f, 0.95f)
        )
    }

}