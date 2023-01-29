package suvorov.openweather.domain.repository

import suvorov.openweather.domain.model.Forecast

interface ForecastRepository {
    suspend fun getForecast(id: Int): List<Forecast>
}