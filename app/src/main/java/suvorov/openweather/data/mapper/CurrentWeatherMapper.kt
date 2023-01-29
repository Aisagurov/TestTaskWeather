package suvorov.openweather.data.mapper

import suvorov.openweather.data.model.CurrentWeatherResponse
import suvorov.openweather.domain.model.CurrentWeather
import suvorov.openweather.util.emptyIfNull

fun CurrentWeatherResponse.toEntity(): CurrentWeather {
    return CurrentWeather(
        weather?.getOrNull(0)?.description.emptyIfNull(""),
        weather?.getOrNull(0)?.icon.emptyIfNull(""),
        main?.temp.emptyIfNull(0.0),
        main?.feelsLike.emptyIfNull(0.0),
        main?.pressure.emptyIfNull(0),
        main?.humidity.emptyIfNull(0),
        visibility.emptyIfNull(0),
        wind?.speed.emptyIfNull(0.0),
        wind?.deg.emptyIfNull(0),
        dt.emptyIfNull(0),
        id.emptyIfNull(0),
        name.emptyIfNull(""),
        sys?.sunrise.emptyIfNull(0),
        sys?.sunset.emptyIfNull(0)
    )
}