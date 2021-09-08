package com.example.weathertest.view.main_screen_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weathertest.R
import com.example.weathertest.databinding.FragmentMainScreenWeatherBinding
import com.example.weathertest.view.actaul_weather_fragment.ActualWeatherFragment
import com.example.weathertest.view.hour_weather_fragment.HourlyWeatherFragment
import com.example.weathertest.view.week_weather_fragment.WeekWeatherFragment

class MainScreenWeatherFragment : Fragment() {

    private lateinit var binding : FragmentMainScreenWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainScreenWeatherBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.actual_weather_container, ActualWeatherFragment())
            ?.replace(R.id.hour_weather_container, HourlyWeatherFragment())
            ?.replace(R.id.week_weather_container, WeekWeatherFragment())
            ?.commit()
    }


}