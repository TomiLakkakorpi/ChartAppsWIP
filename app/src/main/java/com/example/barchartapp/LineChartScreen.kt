package com.example.barchartapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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

@Composable
fun LineChartScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column () {
            Box() {
                val pointsData: List<Point> =
                    listOf(
                        Point(1f, 20f),
                        Point(2f, 70f),
                        Point(3f, 60f),
                        Point(4f, 90f),
                        Point(5f, 10f),
                        Point(6f, 40f),
                        Point(7f, 30f),
                        Point(8f, 2f),
                        Point(9f, 40f),
                        Point(10f, 15f)
                    )

                val steps = 10

                val xAxisData = AxisData.Builder()
                    .axisStepSize(35.dp)
                    .topPadding(105.dp)
                    .steps(pointsData.size - 1)
                    .labelData { i -> pointsData[i].x.toInt().toString() }
                    .labelAndAxisLinePadding(15.dp)
                    .build()

                val yAxisData = AxisData.Builder()
                    .steps(steps)
                    .labelAndAxisLinePadding(20.dp)
                    .labelData { i ->
                        val yMax = 100.0f
                        val yMin = 0.0f
                        //val yMin = pointsData.minOf { it.y }
                        //val yMax = pointsData.maxOf { it.y }
                        val yScale = (yMax - yMin) / steps
                        ((i * yScale) + yMin).formatToSinglePrecision()
                    }.build()

                val data = LineChartData(
                    linePlotData = LinePlotData(
                        lines = listOf(
                            Line(
                                dataPoints = pointsData,
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

            Text(
                modifier = Modifier.clickable {
                    navController.navigateUp()
                },
                text = "Takaisin päävalikkoon",
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
        }
    }
}