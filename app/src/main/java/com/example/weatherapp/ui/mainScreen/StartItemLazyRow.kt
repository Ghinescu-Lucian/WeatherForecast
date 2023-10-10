package com.example.weatherapp.ui.mainScreen

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable

@Composable
fun MyLazyRow() {
    val scrollState = rememberLazyListState()
    LazyRow(
        state = scrollState,
//        initialItem = 3 // Set the initial item to be the 4th item (zero-based index)
    ) {
        items(10) { index ->
            // Your item content here
        }
    }
}