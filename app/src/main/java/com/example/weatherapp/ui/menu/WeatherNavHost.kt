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
import com.example.weatherapp.ui.dailyForecasts.DailyScreen
import com.example.weatherapp.ui.hourlyForecasts.HourlyScreen
import com.example.weatherapp.ui.mainScreen.MainScreen
import com.example.weatherapp.ui.profile.MapScreen
import com.example.weatherapp.ui.profile.ProfileScreen
import com.example.weatherapp.ui.searchScreen.SearchScreen
import com.example.weatherapp.ui.splashScreen.SplashScreen
import com.example.weatherapp.ui.viewModels.MainViewModel
import com.example.weatherapp.ui.viewModels.PointsViewModel
import com.example.weatherapp.ui.viewModels.SearchViewModel
import com.example.weatherapp.ui.viewModels.WeatherViewModel


@Composable
fun WeatherNavHost(
    navController: NavHostController,
    modifier: Modifier,
    viewModelW: MainViewModel,
    context: Context
){
//    val viewModelW : WeatherViewModel = hiltViewModel()

    val state by viewModelW.state.collectAsState()


    NavHost(
        navController = navController,
        startDestination = SplashScreen.route,
        modifier = modifier


    ){

        composable(route =  SplashScreen.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500))
            }
        ){

           SplashScreen(navController = navController)
        }

        composable(route =  Main.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500))
            }
            ){

            Log.d("Main state:", state.toString())
            Log.d("Main City:", viewModelW.point.cityName)
//            var offline = state.online
            Log.d("StateSearch", state.online.toString())
            MainScreen(modifier = Modifier , state = state,
                online = state.online,
               onRefresh = {
                   viewModelW.refresh()
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
            val state by viewModelW.state.collectAsState()
            Log.d("Navigation","Hourly icon pressed")
            HourlyScreen(modifier = Modifier , state = state,
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
            val state by viewModelW.state.collectAsState()
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
            val state by viewModelW.state.collectAsState()


            Log.d("Navigation", "Profile icon pressed")
            ProfileScreen(modifier = Modifier, state = state, context = context,
                navigate = {
                    navController.navigateSingleTopTo(route = Map.route)
                }
            )
        }
        composable(
            route = Search.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500))
            }
        ){
            val state1 by viewModelW.state.collectAsState()



            Log.d("Online12:", "online")
//            val online = state.online
//
//            if(online != null && !online) {
                val viewModelS: SearchViewModel = hiltViewModel()

                Log.d("Navigation", "Search icon pressed")
                if (viewModelW::class.simpleName == "WeatherViewModel") {

                    SearchScreen(modifier = Modifier.fillMaxSize(),
                        viewModel = viewModelS,
                        viewModelWeahter = viewModelW as WeatherViewModel,
                        onClickSearch = {
                            navController.navigateSingleTopTo(route = Main.route)
                        },
                        stateO = state1
                    )
//                }
            }
        }

        composable(
            route  = Map.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500))
            }

        ){

            val pointsViewModel: PointsViewModel = hiltViewModel()
            val navigateBack = {
                navController.navigateSingleTopTo(route = ProfileScreen.route)
//                navController.previousBackStackEntry
//                    ?.savedStateHandle
//                    ?.set("point", point)
//                navController.popBackStack()
            }
            //val point = Weights(1 ,"",1.0,1.0,1.0,1.0,1.0)
            MapScreen(navigateBack = navigateBack, pointsViewModel = pointsViewModel)
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
