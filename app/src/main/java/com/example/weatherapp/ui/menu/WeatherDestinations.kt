package com.example.weatherapp.ui.menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.weatherapp.R

interface WeatherDestination{
    val icon: Int
    val route: String
    val selectedIcon: Int
    val name: Int
}

object Main : WeatherDestination{
    override val route = "main"
    @DrawableRes
    override val icon = R.drawable.ic_home_outlined
    @DrawableRes
    override val selectedIcon = R.drawable.ic_home_filled
    @StringRes
    override val name = R.string.main
}
object HourlyScreen : WeatherDestination{
    override val route = "Hourly"
    @DrawableRes
    override val icon = R.drawable.ic_clock_outlined
    @DrawableRes
    override val selectedIcon = R.drawable.ic_clock_filled
    @StringRes
    override val name = R.string.hourly
}
object Search : WeatherDestination{
    override val route = "Search"
    @DrawableRes
    override val icon = R.drawable.ic_search
    @DrawableRes
    override val selectedIcon = R.drawable.ic_search
    @StringRes
    override val name = R.string.search


}
object DailyScreen : WeatherDestination{
    override val route = "Daily"
    @DrawableRes
    override val icon = R.drawable.ic_daily_outlined
    @DrawableRes
    override val selectedIcon = R.drawable.ic_daily_fill
    @StringRes
    override val name = R.string.daily
}
object ProfileScreen : WeatherDestination{
    override val route = "Profile"
    @DrawableRes
    override val icon = R.drawable.ic_profile_outline
    @DrawableRes
    override val selectedIcon = R.drawable.ic_profile_filled
    @StringRes
    override val name = R.string.profile
}

val menuItems = listOf(
    Main, HourlyScreen,DailyScreen, ProfileScreen
)