package com.example.hiltgradleimplementation.data

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SharedPreferencesViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
): ViewModel() {
    private val _counter = MutableStateFlow(0)
    var counter : StateFlow<Int> = _counter.asStateFlow()
    private var count: Int = 0

    fun incrementCounter(){
        count = getCounter()
        count++
        _counter.value = count
        setCounter()
    }

    private fun getCounter(): Int{
       return  sharedPreferences.getInt("counter", 0)
    }

    private fun setCounter(){
        sharedPreferences.edit().putInt("counter", _counter.value).apply()
        }
}