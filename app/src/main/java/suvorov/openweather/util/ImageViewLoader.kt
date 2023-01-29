package suvorov.openweather.util

import android.widget.ImageView
import coil.load

fun ImageView.get(uri: String) {
    this.load("https://openweathermap.org/img/wn/${uri}.png") {
        crossfade(true)
        crossfade(2000)
    }
}