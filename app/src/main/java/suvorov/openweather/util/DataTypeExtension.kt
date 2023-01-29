package suvorov.openweather.util

fun <T> T?.emptyIfNull(default: T): T {
    return this ?: default
}