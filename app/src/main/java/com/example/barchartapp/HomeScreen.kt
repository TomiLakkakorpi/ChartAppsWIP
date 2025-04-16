package com.example.barchartapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column () {
            Text( // Bar Chart
                modifier = Modifier.clickable {
                    navController.navigate(route = Screen.BarChart.route)
                },
                text = "Pylväsdiagrammi"
            )

            Text( // Pie Chart
                modifier = Modifier.clickable {
                    navController.navigate(route = Screen.PieChart.route)
                },
                text = "Piirakkadiagrammi"
            )

            Text( // Line Chart
                modifier = Modifier.clickable {
                    navController.navigate(route = Screen.LineChart.route)
                },
                text = "Viivadiagrammi"
            )

            Text( // Scatter Plot
                modifier = Modifier.clickable {
                    navController.navigate(route = Screen.ScatterPlot.route)
                },
                text = "Hajakuvaaja"
            )

            Text( // Scatter Plot With Images
                modifier = Modifier.clickable {
                    navController.navigate(route = Screen.ScatterPlotImages.route)
                },
                text = "Hajakuvaaja kuvilla"
            )

            Text( // Stem And Leaf Plot
                modifier = Modifier.clickable {
                    navController.navigate(route = Screen.StemAndLeafPlot.route)
                },
                text = "Stem and leaf -kuva"
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}