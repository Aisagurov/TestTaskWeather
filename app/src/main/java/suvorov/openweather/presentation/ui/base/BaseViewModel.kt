package suvorov.openweather.presentation.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T : ViewState<*>>(
    protected val _stateLiveData: MutableLiveData<T> = MutableLiveData()
) : ViewModel() {
    val stateLiveData: LiveData<T> = _stateLiveData
}