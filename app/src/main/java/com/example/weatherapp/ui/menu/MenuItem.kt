package com.example.weatherapp.ui.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun MenuItem(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit ={}

){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 5.dp)

    )
    {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 10.dp)
//                .height(0.5.dp)
//                .background(MaterialTheme.colorScheme.outline)
//                .align(Alignment.Center)
//
//        )
        Box(modifier = Modifier
            .align(Alignment.Center)
            .size(50.dp)
            .clip(RoundedCornerShape(17.5.dp))
            .background(MaterialTheme.colorScheme.onBackground),
        ) {

            IconButton(
                onClick = onClick,

                modifier = Modifier.fillMaxSize()
                    .align(Alignment.Center) ,

            ){}
                Icon(
                    imageVector = icon,
                    tint = Color.White,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),

                    )


        }
    }
}

@Preview
@Composable
fun MenuItemPreview(){

    WeatherAppTheme {
        MenuItem(ImageVector.vectorResource(R.drawable.ic_search))
    }
}