package com.example.weatherapp.ui.menu

import android.content.Context
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.data.location.geocoder.CitySearch
import com.example.weatherapp.domain.location.SearchInteractor
import com.example.weatherapp.ui.dailyForecasts.DailyScreen
import com.example.weatherapp.ui.Profile.ProfileScreen
import com.example.weatherapp.ui.SearchScreen.SearchScreen
import com.example.weatherapp.ui.hourlyForecasts.HourlyScreen
import com.example.weatherapp.ui.mainScreen.MainScreen
import com.example.weatherapp.ui.states.SearchState
import com.example.weatherapp.ui.viewModels.SearchViewModel
import com.example.weatherapp.ui.viewModels.WeatherViewModel




@Composable
fun WeatherNavHost(
    navController: NavHostController,
    modifier: Modifier,
    context: Context
){
    val viewModel : WeatherViewModel
    viewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Main.route,
        modifier = modifier


    ){
        composable(route =  Main.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500))
            }
            ){
            MainScreen(modifier = Modifier , state = state,
                onClickSeeHourly = {
//                    navController.navigateSingleTopTo(HourlyScreen.route)
                    navController.navigateSingleTopTo(route = HourlyScreen.route)

                }
            )
        }
        composable(route =  HourlyScreen.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500))
            }
        ){
            Log.d("Navigation","Hourly icon pressed")
            HourlyScreen(modifier = Modifier , state = state,
                onClickSeeCurrent = {
//                    navController.navigateSingleTopTo(HourlyScreen.route)
                    navController.navigateSingleTopTo(route = Main.route)

                }
            )
        }
        composable(route = DailyScreen.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500))
            }
        ){
            Log.d("Navigation", "Daily icon pressed")
            DailyScreen(modifier = Modifier, state = state,
                )
        }
        composable(route = ProfileScreen.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500))
            }
        ){
            Log.d("Navigation", "Daily icon pressed")
            ProfileScreen(modifier = Modifier, state = state, context = context)
        }
        composable(route = Search.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500))
            }
        ){
            val viewModel: SearchViewModel = hiltViewModel()
            Log.d("Navigation", "Daily icon pressed")
            SearchScreen(modifier = Modifier.fillMaxSize(), viewModel = viewModel,
                onClickSearch = {
                    navController.navigateSingleTopTo(route = Main.route)
                }
            )
        }


    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route){
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ){
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }