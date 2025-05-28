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
import com.example.barchartapp.ui.theme.color1

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
                onClick = {
                    navController.navigate(route = Screen.BarChart.route)
                }
            ) {
                Text("BarChartAppV1")
            }

            Button(
                onClick = {
                    navController.navigate(route = Screen.LineChart.route)
                }
            ) {
                Text("LineChartAppV1")
            }

            Button(
                onClick = {
                    navController.navigate(route = Screen.WaveChart.route)
                }
            ) {
                Text("WaveChartAppV1")
            }

            Button(
                onClick = {
                    navController.navigate(route = Screen.PieChart.route)
                }
            ) {
                Text("PieChartAppV1")
            }

            Button(
                onClick = {
                    navController.navigate(route = Screen.PieChart2.route)
                }
            ) {
                Text("PieChartAppV2")
            }

            Button(
                onClick = {
                    navController.navigate(route = Screen.PieChart3.route)
                }
            ) {
                Text("PieChartAppV3")
            }

            Button(
                onClick = {
                    navController.navigate(route = Screen.DonutChart.route)
                }
            ) {
                Text("DonutChartAppV1")
            }

            Button(
                onClick = {
                    navController.navigate(route = Screen.BubbleChart.route)
                }
            ) {
                Text("BubbleChartAppV1")
            }

            Button(
                onClick = {
                    navController.navigate(route = Screen.CombinedChart.route)
                }
            ) {
                Text("CombinedChartAppV1")
            }

            Button(
                onClick = {
                    navController.navigate(route = Screen.CustomUIComponent1.route)
                }
            ) {
                Text("CustomUIComponent1")
            }

            Button(
                onClick = {
                    navController.navigate(route = Screen.UserInputExample1.route)
                }
            ) {
                Text("UserInputExample1")
            }
        }
    }
}