package com.example.weathertest.view.actaul_weather_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.weathertest.viewModel.CurrentWeatherViewModel
import com.example.weathertest.databinding.FragmentActualWeatherBinding
import com.example.weathertest.model.model.DataManager
import com.example.weathertest.setIcon
import com.example.weathertest.setWeather
import io.realm.Realm

class ActualWeatherFragment : Fragment() {

    private lateinit var binding: FragmentActualWeatherBinding
    private val weatherViewModel: CurrentWeatherViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         weatherViewModel.getActualWeather()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActualWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        weatherViewModel.actualWeatherViewModel.observe(context as LifecycleOwner,{
            for (r in it) {
                binding.actualTemperature.text = "${r.actualTemperature} \u2109"
                binding.countHumidity.text = r.actualHumidity.toString()
                binding.countUvIndex.text = r.actualUVindex.toString()
                binding.cityName.text = r.actualLocation
                binding.countPressure.text = r.actualPressure.toString()
                binding.speedWind.text = r.actualWind.toString()
                setIcon(r.icon,binding.actualWeatherIcon)
                setWeather(r.icon,binding.actualWeatherLabel,requireContext())
            }
        })
    }
}