package com.example.weatherapp.data.local.cache.json.dtos

import com.example.weatherapp.domain.weather.WeatherType

class ConverterWeatherType {

    fun convertToDto(weatherType: WeatherType): WeatherTypeDto{
        return WeatherTypeDto( weatherDesc = weatherType.weatherDesc, iconRes = weatherType.iconRes)
    }

    fun convertDtoToOriginal(weatherTypeDto: WeatherTypeDto): WeatherType{
        if(WeatherType.HeavyRain.iconRes == weatherTypeDto.iconRes && WeatherType.HeavyRain.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.HeavyRain
        if(WeatherType.Overcast .iconRes == weatherTypeDto.iconRes && WeatherType.Overcast.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.Overcast
        if(WeatherType.HeavyHailThunderstorm .iconRes == weatherTypeDto.iconRes && WeatherType.HeavyHailThunderstorm.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.HeavyHailThunderstorm
        if(WeatherType.ClearSky .iconRes == weatherTypeDto.iconRes && WeatherType.ClearSky.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.ClearSky
        if(WeatherType.SlightHailThunderstorm .iconRes == weatherTypeDto.iconRes && WeatherType.SlightHailThunderstorm.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.SlightHailThunderstorm
        if(WeatherType.ModerateThunderstorm .iconRes == weatherTypeDto.iconRes && WeatherType.ModerateThunderstorm.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.ModerateThunderstorm
        if(WeatherType.HeavySnowShowers .iconRes == weatherTypeDto.iconRes && WeatherType.HeavySnowShowers.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.HeavySnowShowers
        if(WeatherType.HeavySnowFall.iconRes == weatherTypeDto.iconRes && WeatherType.HeavySnowFall.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.HeavySnowFall
        if(WeatherType.HeavyFreezingRain.iconRes == weatherTypeDto.iconRes && WeatherType.HeavyFreezingRain.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.HeavyFreezingRain
        if(WeatherType.ModerateRain .iconRes == weatherTypeDto.iconRes && WeatherType.ModerateRain.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.ModerateRain
        if(WeatherType.ModerateDrizzle .iconRes == weatherTypeDto.iconRes && WeatherType.ModerateDrizzle.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.ModerateDrizzle
        if(WeatherType.ModerateSnowFall.iconRes == weatherTypeDto.iconRes && WeatherType.ModerateSnowFall.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.ModerateSnowFall
        if(WeatherType.ModerateRainShowers .iconRes == weatherTypeDto.iconRes && WeatherType.ModerateRainShowers.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.ModerateRainShowers
        if(WeatherType.SlightSnowShowers .iconRes == weatherTypeDto.iconRes && WeatherType.SlightSnowShowers.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.SlightSnowShowers
        if(WeatherType.SlightRain .iconRes == weatherTypeDto.iconRes && WeatherType.SlightRain.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.SlightRain
        if(WeatherType.SlightSnowFall .iconRes == weatherTypeDto.iconRes && WeatherType.SlightSnowFall.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.SlightSnowFall
        if(WeatherType.SlightRainShowers .iconRes == weatherTypeDto.iconRes && WeatherType.SlightRainShowers.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.SlightRainShowers
        if(WeatherType.ViolentRainShowers .iconRes == weatherTypeDto.iconRes && WeatherType.ViolentRainShowers.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.ViolentRainShowers
        if(WeatherType.SnowGrains .iconRes == weatherTypeDto.iconRes && WeatherType.SnowGrains.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.SnowGrains
        if(WeatherType.DenseFreezingDrizzle .iconRes == weatherTypeDto.iconRes && WeatherType.DenseFreezingDrizzle.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.DenseFreezingDrizzle
        if(WeatherType.LightFreezingDrizzle .iconRes == weatherTypeDto.iconRes && WeatherType.LightFreezingDrizzle.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.LightFreezingDrizzle
        if(WeatherType.LightDrizzle .iconRes == weatherTypeDto.iconRes && WeatherType.LightDrizzle.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.LightDrizzle
        if(WeatherType.DenseDrizzle .iconRes == weatherTypeDto.iconRes && WeatherType.DenseDrizzle.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.DenseDrizzle
        if(WeatherType.DepositingRimeFog .iconRes == weatherTypeDto.iconRes && WeatherType.DepositingRimeFog.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.DepositingRimeFog
        if(WeatherType.Foggy .iconRes == weatherTypeDto.iconRes && WeatherType.Foggy.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.Foggy
        if(WeatherType.PartlyCloudy .iconRes == weatherTypeDto.iconRes && WeatherType.PartlyCloudy.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.PartlyCloudy
        if(WeatherType.MainlyClear .iconRes == weatherTypeDto.iconRes && WeatherType.MainlyClear.weatherDesc == weatherTypeDto.weatherDesc)
            return WeatherType.MainlyClear

        return WeatherType.ClearSky


    }

}