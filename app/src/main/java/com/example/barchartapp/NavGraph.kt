package com.example.barchartapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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

        composable(
            route = Screen.BarChart.route
        ) {
            BarChartScreen(navController)
        }

        composable(
            route = Screen.PieChart.route
        ) {
            PieChartScreen(navController)
        }

        composable(
            route = Screen.LineChart.route
        ) {
            LineChartScreen(navController)
        }

        composable(
            route = Screen.DonutChart.route
        ) {
            DonutChartScreen(navController)
        }

        composable(
            route = Screen.ScatterPlot.route
        ) {
            ScatterPlotScreen(navController)
        }

        composable(
            route = Screen.ScatterPlotImages.route
        ) {
            ScatterPlotImagesScreen(navController)
        }

        composable(
            route = Screen.StemAndLeafPlot.route
        ) {
            StemAndLeafPlotScreen(navController)
        }

        composable(
            route = Screen.BubbleChart.route
        ) {
            BubbleChartScreen(navController)
        }

        composable(
            route = Screen.CustomComponent1.route
        ) {
            CustomComponent1(navController)
        }
    }
}