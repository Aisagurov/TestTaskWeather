package suvorov.openweather.domain.model

data class Forecast(
    val dt: Int,
    val temp: Double,
    val feelsLike: Double,
    val icon: String
)