package com.example.barchartapp

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object BarChart: Screen(route = "barchart_screen")
    object PieChart: Screen(route = "piechart_screen")
    object LineChart: Screen(route = "linechart_screen")
    object DonutChart: Screen(route = "donutchart_screen")
    object ScatterPlot: Screen(route = "scatterplot_screen")
    object ScatterPlotImages: Screen(route = "scatterplotimages_screen")
    object StemAndLeafPlot: Screen(route = "stemandleafplot_screen")
    object BubbleChart: Screen(route = "bubblechart_screen")
    object CustomComponent1: Screen(route = "customcomponent1_screen")
}
