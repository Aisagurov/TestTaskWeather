package suvorov.openweather.presentation.ui.forecast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import suvorov.openweather.databinding.FragmentForecastBinding
import suvorov.openweather.domain.model.Forecast
import suvorov.openweather.presentation.ui.base.BaseFragment
import suvorov.openweather.presentation.ui.base.ViewState
import suvorov.openweather.presentation.ui.forecast.adapter.ForecastAdapter
import suvorov.openweather.util.ErrorHelper

@AndroidEntryPoint
class ForecastFragment: BaseFragment<FragmentForecastBinding>(FragmentForecastBinding::inflate) {

    private val viewModel: ForecastViewModel by viewModels()
    private val forecastAdapter by lazy { ForecastAdapter() }
    private val args: ForecastFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initView()
        viewModel.getForecast(args.id)
    }

    private fun observeViewModel() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            when(it) {
                is ViewState.Success -> {
                    when(val item = it.data) {
                        is List<*> -> {
                            forecastAdapter.submitList(item as List<Forecast>)
                        }
                    }
                    binding.errorTextView.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }
                is ViewState.Error -> {
                    ErrorHelper.errorHandle(requireContext(), it.throwable)
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

    private fun initView() {
        binding.forecastRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = forecastAdapter
        }

        binding.nameTextView.text = args.name

        binding.toolbar.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.errorTextView.setOnClickListener {
            viewModel.getForecast(args.id)
        }
    }
}