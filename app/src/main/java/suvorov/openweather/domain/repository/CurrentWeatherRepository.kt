package suvorov.openweather.domain.repository

import suvorov.openweather.domain.model.CurrentWeather

interface CurrentWeatherRepository {
    suspend fun getCurrentWeather(city: String): CurrentWeather
}