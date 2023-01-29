package suvorov.openweather.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import suvorov.openweather.data.model.CurrentWeatherResponse
import suvorov.openweather.data.model.ForecastResponse

interface ApiService {
    @GET("weather?")
    suspend fun getCurrentWeather(@Query("q") cityName: String): CurrentWeatherResponse

    @GET("forecast?")
    suspend fun getForecast(@Query("id") cityId: Int): ForecastResponse
}