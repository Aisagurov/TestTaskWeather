package suvorov.openweather.data.repository

import suvorov.openweather.data.remote.ApiService
import suvorov.openweather.data.mapper.toEntity

import suvorov.openweather.domain.repository.ForecastRepository
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val service: ApiService
): ForecastRepository {
    override suspend fun getForecast(id: Int) = service.getForecast(id).list.map { it.toEntity() }
}