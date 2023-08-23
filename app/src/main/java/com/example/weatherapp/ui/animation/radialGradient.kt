package com.example.weatherapp.ui.animation

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush

class radialGradient(val colorStart: Color,
                     val colorEnd: Color
                     ) : ShaderBrush(){
    override fun createShader(size: Size): Shader {
        val biggerDimension = size.height * 1.35f
        return RadialGradientShader(
            colors = listOf( colorEnd, colorStart),
            center = size.center,
            radius = biggerDimension / 2f,
            colorStops = listOf(0f, 0.95f)
        )
    }

}