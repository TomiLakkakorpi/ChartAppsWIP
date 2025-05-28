package com.example.barchartapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import co.yml.charts.common.extensions.formatToSinglePrecision

var lineChartListIndex = 0F
var lineChartList = mutableListOf<Point>()

@Composable
fun LineChartScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(10.dp, 20.dp, 10.dp, 0.dp),
                textAlign = TextAlign.Center,
                text = "Nokian osake Tammikuu 2024 - Joulukuu 2024",
                fontSize = 15.sp
            )

            DrawLineChart()

            Box(
                modifier = Modifier.padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        navController.navigateUp()
                    }
                ) {
                    Text("Takaisin päävalikkoon")
                }
            }
        }
    }
}

@Composable
fun DrawLineChart() {
    val dataList = arrayListOf(
        Point(1f, 3.332f),
        Point(2f, 3.260f),
        Point(3f, 3.291f),
        Point(4f, 3.412f),
        Point(5f, 3.591f),
        Point(6f, 3.558f),
        Point(7f, 3.621f),
        Point(8f, 3.978f),
        Point(9f, 3.924f),
        Point(10f, 4.325f),
        Point(11f, 3.980f),
        Point(12f, 4.274f)
    )

    val monthList = arrayListOf(
        "Tammi",
        "Helmi",
        "Maalis",
        "Huhti",
        "Touko",
        "Kesä",
        "Heinä",
        "Elo",
        "Syys",
        "Loka",
        "Marras",
        "Joulu",
        ""
    )

    val yAxisSteps = 10

    val xAxisData = AxisData.Builder()
        .axisStepSize(50.dp)
        .topPadding(105.dp)
        .steps(dataList.size - 1)
        .axisLabelFontSize(15.sp)
        .labelData { i -> monthList[i].toString() }
        .labelAndAxisLinePadding(25.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(yAxisSteps)
        .labelAndAxisLinePadding(30.dp)
        .labelData { i ->
            val yMin =
                dataList.minOf { it.y }
            val yMax =
                dataList.maxOf { it.y }
            val yScale = (yMax - yMin) / yAxisSteps
            ((i * yScale) + yMin).formatToSinglePrecision() + " €"
        }.build()

    val data = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = dataList,
                    LineStyle(),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines()
    )

    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = data
    )
}