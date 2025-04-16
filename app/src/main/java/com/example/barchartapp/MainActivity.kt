package com.example.barchartapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.yml.charts.ui.barchart.BarChart
import com.example.barchartapp.ui.theme.BarChartAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BarChartAppTheme {
                //BarChartRandomValues()

                //BarChartManualValues()

                //LineChartRandomValues()

                //PieChartRandomValues()

                PieChartManualValues()
                }
        }
    }
}

