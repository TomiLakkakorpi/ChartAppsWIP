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
    object GraphingCalculatorScreen1: Screen(route = "graphingcalculator1_screen")
    object GraphingCalculatorScreen2: Screen(route = "graphingcalculator2_screen")
    object GraphingCalculatorScreen3: Screen(route = "graphingcalculator3_screen")
    object GraphingCalculatorScreen4: Screen(route = "graphingcalculator4_screen")
    object GraphingCalculatorScreen5: Screen(route = "graphingcalculator5_screen")
    object GraphingCalculatorScreen6: Screen(route = "graphingcalculator6_screen")
}
