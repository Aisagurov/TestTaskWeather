package suvorov.openweather.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentWeather(
    val description: String,
    val icon: String,
    val temp: Double,
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    val visibility: Int,
    val windSpeed: Double,
    val windDeg: Int,
    val dt: Int,
    val id: Int,
    val name: String,
    val sunrise: Int,
    val sunset: Int
): Parcelable