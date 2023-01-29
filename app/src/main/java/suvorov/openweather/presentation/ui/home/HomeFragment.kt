package suvorov.openweather.presentation.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import suvorov.openweather.R
import suvorov.openweather.databinding.FragmentHomeBinding
import suvorov.openweather.domain.model.CurrentWeather
import suvorov.openweather.domain.repository.PreferenceStorage
import suvorov.openweather.presentation.ui.base.BaseFragment
import suvorov.openweather.presentation.ui.base.ViewState
import suvorov.openweather.util.ErrorHelper.errorHandle
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    @Inject lateinit var preferences: PreferenceStorage

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initCitiesAdapter()
        loadData()
    }

    private fun observeViewModel() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            when(it) {
                is ViewState.Success -> {
                    when(val item = it.data) {
                        is CurrentWeather -> {
                            initView(item)
                        }
                    }
                    binding.errorTextView.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.infoLayout.visibility = View.VISIBLE
                }
                is ViewState.Error -> {
                    errorHandle(requireContext(), it.throwable)
                    binding.infoLayout.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.errorTextView.visibility = View.VISIBLE
                }
                is ViewState.Loading -> {
                    binding.errorTextView.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initView(item: CurrentWeather) {
        binding.apply {
            tempTextView.text =
                "${item.temp.toInt()}${getString(R.string.degree_celsius)}"
            humidityTextView.text =
                "${item.humidity}${getString(R.string.percent)}"
            windTextView.text =
                "${item.windSpeed}${getString(R.string.meters_per_second)}"
            pressureTextView.text =
                "${item.pressure.times(0.75).toInt()}${getString(R.string.millimeters_of_mercury_post)}"
        }

        binding.currentButton.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToCurrentWeatherFragment(item)
            )
        }

        binding.forecastButton.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToForecastFragment(item.id, item.name)
            )
        }
    }

    private fun initCitiesAdapter() {
        ArrayAdapter.createFromResource(requireContext(), R.array.cities, R.layout.spinner_item).also {
            it.setDropDownViewResource(R.layout.spinner_drop_item)
            binding.citiesSpinner.adapter = it
        }
        binding.citiesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val city = resources.getStringArray(R.array.cities)[position]
                viewModel.getCurrentWeather(city)
                initErrorClick(city)
                preferences.spinnerPosition = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun initErrorClick(city: String) {
        binding.errorTextView.setOnClickListener {
            viewModel.getCurrentWeather(city)
        }
    }

    private fun loadData() {
        binding.citiesSpinner.setSelection(preferences.spinnerPosition)
    }
}