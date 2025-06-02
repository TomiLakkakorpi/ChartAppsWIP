package com.example.barchartapp

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object BarChart: Screen(route = "barchart_screen")
    object LineChart: Screen(route = "linechart_screen")
    object WaveChart: Screen(route = "wavechart_screen")
    object PieChart: Screen(route = "piechart_screen")
    object PieChart2: Screen(route = "piechart_screen2")
    object PieChart3: Screen(route = "piechart_screen3")
    object DonutChart: Screen(route = "donutchart_screen")
    object BubbleChart: Screen(route = "bubblechart_screen")
    object CombinedChart: Screen(route = "combinedchart_screen")
    object CustomUIComponent1: Screen(route = "customuicomponent1_screen")
    object UserInputExample1: Screen(route = "userinputexample1_screen")
    object UserInputExample2: Screen(route = "userinputexample2_screen")
    object TestScreen: Screen(route = "test_screen")
}
