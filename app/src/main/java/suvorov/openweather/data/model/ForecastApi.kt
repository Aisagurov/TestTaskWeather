package suvorov.openweather.data.model

data class ForecastApi(
    val dt: Int?,
    val main: MainApi?,
    val weather: List<WeatherApi>?
)