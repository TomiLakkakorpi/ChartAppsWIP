package com.example.barchartapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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

            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    navController.navigate(route = Screen.BarChart.route)
                }
            ) {
                Text("1. Pylväsdiagrammi")
            }

            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    navController.navigate(route = Screen.LineChart.route)
                }
            ) {
                Text("2. Viivadiagrammi")
            }

            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    navController.navigate(route = Screen.WaveChart.route)
                }
            ) {
                Text("3. Aaltodiagrammi")
            }

            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    navController.navigate(route = Screen.PieChart.route)
                }
            ) {
                Text("4. Piirakkadiagrammi")
            }

            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    navController.navigate(route = Screen.DonutChart.route)
                }
            ) {
                Text("5. Donitsidiagrammi")
            }

            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    navController.navigate(route = Screen.BubbleChart.route)
                }
            ) {
                Text("6. Bubble Chart")
            }

            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    navController.navigate(route = Screen.CombinedChart.route)
                }
            ) {
                Text("7. Yhdistetty diagrammi")
            }

            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    navController.navigate(route = Screen.CustomUIComponent1.route)
                }
            ) {
                Text("8. Datankäyttö esimerkki")
            }

            Button(
                onClick = {
                    navController.navigate(route = Screen.WeightTrackerScreen.route)
                }
            ) {
                Text("9. Painon seuranta sovellus")
            }
        }
    }
}