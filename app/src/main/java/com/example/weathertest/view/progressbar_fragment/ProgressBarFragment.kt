package com.example.weathertest.view.progressbar_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.weathertest.view.main_screen_fragment.MainScreenWeatherFragment
import com.example.weathertest.R
import com.example.weathertest.checkPermission
import com.example.weathertest.databinding.FragmentProgressBarBinding
import com.example.weathertest.model.model.DataManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class ProgressBarFragment : Fragment() {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var binding:FragmentProgressBarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        binding = FragmentProgressBarBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if((requireContext() as AppCompatActivity).checkPermission())
            fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                val location = it.result
                DataManager.getWeatherData(location.latitude, location.longitude)
            }

         activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.main_activity_place_holder, MainScreenWeatherFragment())
            ?.commit()
    }
}

