package com.example.barchartapp

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.*

@Composable
fun BarChartRandomValues() {
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


@Composable
fun BarChartManualValues() {
    val maxRange = 1000000
    val yStepSize = 10

    val list = arrayListOf(
        BarData(
            point = Point(1f, 681802F),
            Color.Gray,
            label = "Helsinki",),
        BarData(
            point = Point(2F, 318507F),
            Color.Gray,
            label = "Espoo",),
        BarData(
            point = Point(3F, 258770F),
            Color.Gray,
            label = "Tampere",),
        BarData(
            point = Point(4F, 250073F),
            Color.Gray,
            label = "Vantaa",),
        BarData(
            point = Point(5F, 215530F),
            Color.Gray,
            label = "Oulu",),
        /*BarData(
            point = Point(6F, 204618F),
            Color.Blue,
            label = "Turku",),
        BarData(
            point = Point(7F, 148622F),
            Color.Blue,
            label = "Jyväskylä",),
        BarData(
            point = Point(8F, 124825F),
            Color.Blue,
            label = "Kuopio",),
        BarData(
            point = Point(9F, 121202F),
            Color.Blue,
            label = "Lahti",),
        BarData(
            point = Point(10F, 83334F),
            Color.Blue,
            label = "Pori",),*/
    )

    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(list.size - 1)
        .bottomPadding(40.dp)
        .axisLabelAngle(20f)
        .startDrawPadding(48.dp)
        .labelData { index -> list[index].label }
        .build()

    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(10.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()

    val barChartData = BarChartData(
        chartData = list,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        barStyle = BarStyle(
            paddingBetweenBars = 25.dp,
            barWidth = 20.dp
        ),
        showYAxis = true,
        showXAxis = true,
        horizontalExtraSpace = 20.dp
    )
    BarChart(modifier = Modifier.height(350.dp), barChartData = barChartData)
}
