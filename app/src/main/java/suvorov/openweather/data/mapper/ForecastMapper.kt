package suvorov.openweather.data.mapper

import suvorov.openweather.data.model.ForecastApi
import suvorov.openweather.domain.model.Forecast
import suvorov.openweather.util.emptyIfNull

fun ForecastApi.toEntity(): Forecast {
    return Forecast(
        dt.emptyIfNull(0),
        main?.temp.emptyIfNull(0.0),
        main?.feelsLike.emptyIfNull(0.0),
        weather?.getOrNull(0)?.icon.emptyIfNull("")
    )
}