package com.example.weathertest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathertest.database.HourWeatherCondition
import com.example.weathertest.databinding.FragmentHourlyWeatherBinding
import io.realm.Realm

class HourlyWeatherFragment : Fragment() {

    private lateinit var binding: FragmentHourlyWeatherBinding
    private lateinit var realm : Realm
    private lateinit var adapter: HourlyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realm = Realm.getDefaultInstance()
        adapter = HourlyAdapter(realm.where(HourWeatherCondition::class.java).findAll())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHourlyWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.RecyclerViewHour
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = adapter

    }
}