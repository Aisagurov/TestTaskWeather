package suvorov.openweather.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import suvorov.openweather.data.local.SharedPreferenceStorage
import suvorov.openweather.data.remote.ApiService
import suvorov.openweather.data.repository.CurrentWeatherRepositoryImpl
import suvorov.openweather.data.repository.ForecastRepositoryImpl
import suvorov.openweather.domain.repository.CurrentWeatherRepository
import suvorov.openweather.domain.repository.ForecastRepository
import suvorov.openweather.domain.repository.PreferenceStorage
import suvorov.openweather.util.Constants.API_KEY
import suvorov.openweather.util.Constants.BASE_URL
import suvorov.openweather.util.Constants.LANGUAGE_RU
import suvorov.openweather.util.Constants.METRIC
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /** Network */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = Interceptor { chain ->
            val newUrl = chain.request().url.newBuilder()
                .addQueryParameter("appid", API_KEY)
                .addQueryParameter("units", METRIC)
                .addQueryParameter("lang", LANGUAGE_RU)
                .build()

            val newRequest = chain.request().newBuilder().url(newUrl).build()

            chain.proceed(newRequest)
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    /** SharedPreferences */
    @Provides
    @Singleton
    fun providePreferenceStorage(@ApplicationContext context: Context): PreferenceStorage {
        return SharedPreferenceStorage(context)
    }

    /** Repositories */
    @Provides
    @Singleton
    fun provideCurrentWeatherRepository(service: ApiService): CurrentWeatherRepository {
        return CurrentWeatherRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideForecastRepository(service: ApiService): ForecastRepository {
        return ForecastRepositoryImpl(service)
    }
}