package com.example.barchartapp

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.*

@Composable
fun RandomBarChart() {
    val maxRange = 100
    val barData = DataUtils.getGradientBarChartData(50, 100)
    val yStepSize = 10

    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(barData.size - 1)
        .bottomPadding(40.dp)
        .topPadding(20.dp)
        .axisLabelAngle(20f)
        .startDrawPadding(48.dp)
        .labelData { index -> barData[index].label }
        .build()

    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()

    val barChartData = BarChartData(
        chartData = barData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        barStyle = BarStyle(paddingBetweenBars = 20.dp,
            barWidth = 35.dp,
            //isGradientEnabled = true,
            selectionHighlightData = SelectionHighlightData(
                highlightBarColor = Color.Black,
                highlightTextBackgroundColor = Color.Green,
                popUpLabel = { _, y -> " Value : $y " }
            )),
        showYAxis = true,
        showXAxis = true,
        horizontalExtraSpace = 20.dp
    )
    BarChart(modifier = Modifier.height(400.dp), barChartData = barChartData)
}
