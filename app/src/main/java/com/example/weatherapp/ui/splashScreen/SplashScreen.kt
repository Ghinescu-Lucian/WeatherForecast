package com.example.weatherapp.ui.splashScreen

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.weatherapp.R
import com.example.weatherapp.ui.menu.Main
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // Your splash screen content goes here
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        val imageLoader = ImageLoader.Builder(LocalContext.current)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()

        Image(
            painter = rememberAsyncImagePainter(R.drawable.sungif, imageLoader),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )


            LaunchedEffect(key1 = true) {
                delay(2000) // Adjust the delay duration as needed (in milliseconds)
                navController.navigate(Main.route) // Navigate to your main screen
            }

    }
}