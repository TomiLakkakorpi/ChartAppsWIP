package com.example.barchartapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.barchartapp.Screen.UserInputExample1

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }

        // Bar Chart
        composable(
            route = Screen.BarChart.route
        ) {
            BarChartScreen(navController)
        }

        // Line Chart
        composable(
            route = Screen.LineChart.route
        ) {
            LineChartScreen(navController)
        }

        // Wave Chart
        composable(
            route = Screen.WaveChart.route
        ) {
            WaveChartScreen(navController)
        }

        // Pie Chart 1
        composable(
            route = Screen.PieChart.route
        ) {
            PieChartScreen(navController)
        }

        // Pie Chart 2
        composable(
            route = Screen.PieChart2.route
        ) {
            PieChartScreen2(navController)
        }

        // Pie Chart 3
        composable(
            route = Screen.PieChart3.route
        ) {
            PieChartScreen3(navController)
        }

        // Donut Chart
        composable(
            route = Screen.DonutChart.route
        ) {
            DonutChartScreen(navController)
        }

        // Bubble Chart
        composable(
            route = Screen.BubbleChart.route
        ) {
            BubbleChartScreen(navController)
        }

        // Combined Chart
        composable(
            route = Screen.CombinedChart.route
        ) {
            CombinedChartScreen(navController)
        }

        // Data usage example
        composable(
            route = Screen.CustomUIComponent1.route
        ) {
            CustomUIComponent1(navController)
        }

        // Weight tracker example
        composable(
            route = Screen.UserInputExample1.route
        ) {
            UserInputExample1(navController)
        }
    }
}