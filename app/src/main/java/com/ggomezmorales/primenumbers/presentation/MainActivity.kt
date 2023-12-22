package com.ggomezmorales.primenumbers.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ggomezmorales.primenumbers.presentation.ui.Home

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val primeCalculatorModel = PrimeCalculatorModel()
        super.onCreate(savedInstanceState)
        setContent {
            Home(primeCalculatorModel)
        }
    }
}
