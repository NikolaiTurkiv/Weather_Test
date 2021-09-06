package com.example.weathertest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathertest.database.HourWeatherCondition
import com.example.weathertest.database.WeekWeatherCondition
import com.example.weathertest.databinding.ActivityMainBinding.inflate
import com.example.weathertest.databinding.FragmentWeekWeatherBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.realm.Realm

class WeekWeatherFragment : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentWeekWeatherBinding
    private lateinit var realm : Realm
    private lateinit var adapter: WeekAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realm = Realm.getDefaultInstance()
        adapter = WeekAdapter(realm.where(WeekWeatherCondition::class.java).findAll())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeekWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.RecyclerViewWeek
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = adapter

    }

    companion object{
        val TAG_TO_BOTTOM_FRAGMENT = "bottom_fragment"
    }

}