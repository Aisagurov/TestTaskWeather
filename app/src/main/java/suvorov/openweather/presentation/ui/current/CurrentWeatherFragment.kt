package suvorov.openweather.presentation.ui.current

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import suvorov.openweather.R
import suvorov.openweather.databinding.FragmentCurrentBinding
import suvorov.openweather.presentation.ui.base.BaseFragment
import suvorov.openweather.util.convertingDegrees
import suvorov.openweather.util.get
import suvorov.openweather.util.unixTimestampToTimeString

@AndroidEntryPoint
class CurrentWeatherFragment: BaseFragment<FragmentCurrentBinding>(FragmentCurrentBinding::inflate) {

    private val args: CurrentWeatherFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val item = args.currentWeather

        binding.apply {
            nameTextView.text = item.name
            iconImageView.get(item.icon)
            descriptionTextView.text = item.description
            tempTextView.text = "${item.temp.toInt()}${getString(R.string.degree_celsius)}"
            feelsLikeTextView.text =
                "${getString(R.string.feels_like)} ${item.feelsLike.toInt()}${getString(R.string.degree_celsius)}"
            sunriseTextView.text = item.sunrise.unixTimestampToTimeString()
            sunsetTextView.text = item.sunset.unixTimestampToTimeString()
            windTextView.text = "${item.windSpeed}${getString(R.string.meters_per_second)}"
            degImageView.convertingDegrees(item.windDeg)
            pressureTextView.text =
                "${item.pressure.times(0.75).toInt()}${getString(R.string.millimeters_of_mercury_post)}"
            humidityTextView.text = "${item.humidity}${getString(R.string.percent)}"
            visibilityTextView.text = "${item.visibility.div(1000)}${getString(R.string.km)}"
        }

        binding.toolbar.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}