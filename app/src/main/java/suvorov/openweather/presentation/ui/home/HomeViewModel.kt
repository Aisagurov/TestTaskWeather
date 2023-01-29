package suvorov.openweather.presentation.ui.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import suvorov.openweather.domain.repository.CurrentWeatherRepository
import suvorov.openweather.presentation.ui.base.BaseViewModel
import suvorov.openweather.presentation.ui.base.ViewState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CurrentWeatherRepository
): BaseViewModel<ViewState<*>>() {
    fun getCurrentWeather(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateLiveData.postValue(ViewState.Loading)
            kotlin.runCatching {
                repository.getCurrentWeather(city)
            }.onSuccess {
                _stateLiveData.postValue(ViewState.Success(it))
            }.onFailure {
                _stateLiveData.postValue(ViewState.Error(it))
            }
        }
    }
}