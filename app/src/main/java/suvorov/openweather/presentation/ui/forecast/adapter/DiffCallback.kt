package suvorov.openweather.presentation.ui.forecast.adapter

import androidx.recyclerview.widget.DiffUtil
import suvorov.openweather.domain.model.Forecast

object DiffCallback: DiffUtil.ItemCallback<Forecast>() {
    override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
        return oldItem.dt == newItem.dt
    }
}