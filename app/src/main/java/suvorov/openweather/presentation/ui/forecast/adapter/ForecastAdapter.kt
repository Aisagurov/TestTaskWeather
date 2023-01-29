package suvorov.openweather.presentation.ui.forecast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import suvorov.openweather.databinding.ForecastItemBinding
import suvorov.openweather.domain.model.Forecast

class ForecastAdapter:
    ListAdapter<Forecast, ForecastViewHolder>(AsyncDifferConfig.Builder(DiffCallback).build())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(
            ForecastItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}