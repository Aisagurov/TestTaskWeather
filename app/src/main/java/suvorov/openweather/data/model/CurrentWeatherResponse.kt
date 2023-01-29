package suvorov.openweather.data.model

data class CurrentWeatherResponse(
    val weather: List<WeatherApi>?,
    val main: MainApi?,
    val visibility: Int?,
    val wind: WindApi?,
    val dt: Int?,
    val sys: SysApi?,
    val id: Int?,
    val name: String?
)