package com.example.barchartapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column () {

            // Header
            Text(
                text = "Diagrammit",
                fontSize = 25.sp,
                modifier = Modifier.padding(10.dp)
            )

            // Bar Chart
            Text(
                text = "1. Pylväsdiagrammi",
                fontSize = 20.sp,
                color = Color.Green,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { navController.navigate(route = Screen.BarChart.route) }
            )

            // Line Chart
            Text(
                text = "2. Viivadiagrammi",
                fontSize = 20.sp,
                color = Color.Green,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { navController.navigate(route = Screen.LineChart.route) }
            )

            // Line Chart
            Text(
                text = "3. Aaltodiagrammi",
                fontSize = 20.sp,
                color = Color.Green,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { navController.navigate(route = Screen.WaveChart.route) }
            )

            // Pie Chart
            Text(
                text = "4. Piirakkadiagrammi",
                fontSize = 20.sp,
                color = Color.Green,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { navController.navigate(route = Screen.PieChart.route) }
            )

            // Line Chart
            Text(
                text = "5. Donitsidiagrammi",
                fontSize = 20.sp,
                color = Color.Green,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { navController.navigate(route = Screen.DonutChart.route) }
            )

            // Bubble Chart
            Text(
                text = "6. Bubble Chart",
                fontSize = 20.sp,
                color = Color.Green,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { navController.navigate(route = Screen.BubbleChart.route) }
            )

            // Line Chart
            Text(
                text = "7. Yhdistetty diagrammi",
                fontSize = 20.sp,
                color = Color.Green,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { navController.navigate(route = Screen.CombinedChart.route) }
            )

            // Custom Component 1
            Text(
                text = "8. Datankäyttö esimerkki",
                fontSize = 20.sp,
                color = Color.Green,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { navController.navigate(route = Screen.CustomUIComponent1.route) }
            )

            // Scatter Plot With Images
            Text(
                text = "9. Painon seuranta sovellus",
                fontSize = 20.sp,
                color = Color.Red,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { navController.navigate(route = Screen.WeightTrackerScreen.route) }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}