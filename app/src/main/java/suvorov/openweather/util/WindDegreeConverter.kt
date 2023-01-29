package suvorov.openweather.util

import android.widget.ImageView
import suvorov.openweather.R

fun ImageView.convertingDegrees(degree: Int?) {
    setImageResource(
        when (degree) {
            in 0..22 -> R.drawable.baseline_south
            in 23..67 -> R.drawable.baseline_south_west
            in 68..112 -> R.drawable.baseline_west
            in 113..157 -> R.drawable.baseline_north_west
            in 158..202 -> R.drawable.baseline_north
            in 203..247 -> R.drawable.baseline_north_east
            in 248..292 -> R.drawable.baseline_east
            in 293..337 -> R.drawable.baseline_south_east
            in 338..360 -> R.drawable.baseline_south
            else -> R.drawable.baseline_minimize
        }
    )
}