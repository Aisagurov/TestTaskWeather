package suvorov.openweather.data.local

import android.content.Context
import android.content.SharedPreferences
import suvorov.openweather.domain.repository.PreferenceStorage
import suvorov.openweather.util.Constants.DEFAULT_POSITION
import suvorov.openweather.util.Constants.NAME_PREFERENCES
import suvorov.openweather.util.Constants.SPINNER_POSITION
import javax.inject.Inject

class SharedPreferenceStorage @Inject constructor(context: Context): PreferenceStorage {

    private val preferences: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(NAME_PREFERENCES, Context.MODE_PRIVATE)
    }

    override var spinnerPosition by IntPreferences(preferences, SPINNER_POSITION, DEFAULT_POSITION)
}