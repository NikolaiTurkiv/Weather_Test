package com.example.weathertest.view.week_weather_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathertest.databinding.FragmentWeekWeatherBinding
import com.example.weathertest.viewModel.CurrentWeatherViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WeekWeatherFragment : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentWeekWeatherBinding
    private lateinit var adapter: WeekAdapter
    private val weatherViewModel: CurrentWeatherViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = WeekAdapter()
        weatherViewModel.getWeeklyWeather()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        weatherViewModel.weeklyWeatherViewModel.observe(context as LifecycleOwner,{
            adapter.refreshDays(it)
        })
        binding = FragmentWeekWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.weekListWeather
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = adapter
    }
}