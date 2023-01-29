package suvorov.openweather.data.model

import com.google.gson.annotations.SerializedName

data class MainApi(
    val temp: Double?,
    @SerializedName("feels_like") val feelsLike: Double?,
    val pressure: Int?,
    val humidity: Int?
)