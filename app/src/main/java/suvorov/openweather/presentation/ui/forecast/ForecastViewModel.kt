package suvorov.openweather.presentation.ui.forecast

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import suvorov.openweather.domain.repository.ForecastRepository
import suvorov.openweather.presentation.ui.base.BaseViewModel
import suvorov.openweather.presentation.ui.base.ViewState
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val repository: ForecastRepository
): BaseViewModel<ViewState<*>>() {
    fun getForecast(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateLiveData.postValue(ViewState.Loading)
            kotlin.runCatching {
                repository.getForecast(id)
            }.onSuccess {
                _stateLiveData.postValue(ViewState.Success(it))
            }.onFailure {
                _stateLiveData.postValue(ViewState.Error(it))
            }
        }
    }
}