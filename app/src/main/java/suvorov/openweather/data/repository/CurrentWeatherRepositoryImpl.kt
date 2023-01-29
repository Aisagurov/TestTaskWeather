package suvorov.openweather.data.repository

import suvorov.openweather.data.remote.ApiService
import suvorov.openweather.data.mapper.toEntity
import suvorov.openweather.domain.repository.CurrentWeatherRepository
import javax.inject.Inject

class CurrentWeatherRepositoryImpl @Inject constructor(
    private val service: ApiService
): CurrentWeatherRepository {
    override suspend fun getCurrentWeather(city: String) = service.getCurrentWeather(city).toEntity()
}