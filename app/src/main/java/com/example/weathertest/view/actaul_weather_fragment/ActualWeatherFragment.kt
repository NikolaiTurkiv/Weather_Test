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
import com.example.weathertest.utils.setIcon
import com.example.weathertest.utils.setWeather

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
    ): View {
        binding = FragmentActualWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        weatherViewModel.actualWeatherViewModel.observe(context as LifecycleOwner,{

            it.forEach {
                binding.actualTemperature.text = "${it.actualTemperature} \u2109"
                binding.countHumidity.text = it.actualHumidity.toString()
                binding.countUvIndex.text = it.actualUVindex.toString()
                binding.cityName.text = it.actualLocation
                binding.countPressure.text = it.actualPressure.toString()
                binding.speedWind.text = it.actualWind.toString()
                setIcon(it.icon,binding.actualWeatherIcon)
                setWeather(it.icon,binding.actualWeatherLabel,requireContext())
            }
        })
    }
}