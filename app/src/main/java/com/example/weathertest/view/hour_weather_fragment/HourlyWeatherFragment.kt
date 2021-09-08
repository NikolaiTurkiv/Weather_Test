package com.example.weathertest.view.hour_weather_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathertest.databinding.FragmentHourlyWeatherBinding
import com.example.weathertest.viewModel.CurrentWeatherViewModel

class HourlyWeatherFragment : Fragment() {

    private lateinit var binding: FragmentHourlyWeatherBinding
    private lateinit var adapter: HourlyAdapter
    private val weatherViewModel: CurrentWeatherViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = HourlyAdapter()
        weatherViewModel.getHourlyWeather()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        weatherViewModel.hourlyWeatherViewModel.observe(context as LifecycleOwner,{
            adapter.refreshHours(it)
        })
        binding = FragmentHourlyWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.listOfHours
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = adapter

    }
}