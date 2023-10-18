package com.example.weatherapp

import com.example.weatherapp.domain.weather.Interactors.AverageCalculator
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    val averageCalculator = mockk<AverageCalculator>()

    @Test
    fun addition_isCorrect() {




        assertEquals(4, 2 + 2)
    }
}