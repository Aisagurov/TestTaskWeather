package suvorov.openweather.presentation.ui.forecast.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import suvorov.openweather.R
import suvorov.openweather.databinding.ForecastItemBinding
import suvorov.openweather.domain.model.Forecast
import suvorov.openweather.util.get
import suvorov.openweather.util.unixTimestampToDateTimeString

class ForecastViewHolder(
    private val binding: ForecastItemBinding
): RecyclerView.ViewHolder(binding.root) {
    private val context = binding.root.context
    @SuppressLint("SetTextI18n")
    fun bind(item: Forecast) {
        binding.apply {
            dateTextView.text =
                item.dt.unixTimestampToDateTimeString()
            tempTextView.text =
                "${item.temp.toInt()}${context.getString(R.string.degree_celsius)}"
            feelsLikeTextView.text =
                "${item.feelsLike.toInt()}${context.getString(R.string.degree_celsius)}"
            iconImageView.get(item.icon)
        }
    }
}