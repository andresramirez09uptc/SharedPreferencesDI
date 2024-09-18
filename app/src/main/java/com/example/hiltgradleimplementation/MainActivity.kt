package com.example.hiltgradleimplementation

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.hiltgradleimplementation.data.SharedPreferencesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val sharedPreferencesViewModel: SharedPreferencesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferencesViewModel.incrementCounter()
        lifecycleScope.launch {
            sharedPreferencesViewModel.counter.collect { counter ->
                val counterTextView = findViewById<TextView>(R.id.counterTextView)
                counterTextView.text = counter.toString()
            }
        }
    }
}