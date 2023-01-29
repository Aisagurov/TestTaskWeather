package suvorov.openweather.presentation.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.findNavController
import suvorov.openweather.databinding.FragmentSplashBinding
import suvorov.openweather.presentation.ui.base.BaseFragment

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment: BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(
                SplashScreenFragmentDirections.actionSplashScreenFragmentToHomeFragment())
        }, 1000)
    }
}