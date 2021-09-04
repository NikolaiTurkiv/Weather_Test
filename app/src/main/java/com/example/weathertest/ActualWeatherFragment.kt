package com.example.weathertest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weathertest.database.WeatherCondition
import com.example.weathertest.databinding.FragmentActualWeatherBinding
import io.realm.Realm

class ActualWeatherFragment : Fragment() {

    private lateinit var binding: FragmentActualWeatherBinding
    private lateinit var realm : Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realm = Realm.getDefaultInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActualWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val result:List<WeatherCondition> = realm.where(WeatherCondition::class.java).findAll()
        for(r in result){
            binding.textViewActualTemperature.text =  "${r.actualTemperature} \u2109"
            binding.textViewCountHumidity.text = r.actualHumidity.toString()
            binding.textViewCountUvIndex.text=r.actualUVindex.toString()
            binding.textViewCityName.text = r.actualLocation
            binding.textViewCountPressure.text = r.actualPressure.toString()
            binding.textViewSpeedWind.text=r.actualWind.toString()
        }
    }
}