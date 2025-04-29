package com.example.barchartapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

//YCharts Imports
import co.yml.charts.common.model.Point
import co.yml.charts.axis.AxisData
import co.yml.charts.common.extensions.formatToSinglePrecision
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.bubblechart.model.BubbleChartData
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.bubblechart.BubbleChart

@Composable
fun BubbleChartScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .height(800.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            Box() {
                val pointsData: List<Point> =
                    listOf(
                        Point(1f, 25f),
                        Point(2f, 73f),
                        Point(3f, 68f),
                        Point(4f, 100f),
                        Point(5f, 17f),
                        Point(6f, 44f),
                        Point(7f, 31f),
                        Point(8f, 2f),
                        Point(9f, 49f),
                        Point(10f, 15f)
                    )

                val steps = 5
                val xAxisData = AxisData.Builder()
                    .axisStepSize(30.dp)
                    .steps(pointsData.size -1)
                    .labelData { i ->pointsData[i].x.toInt().toString() }
                    .labelAndAxisLinePadding(20.dp)
                    .build()

                val yAxisData = AxisData.Builder()
                    .steps(steps)
                    .labelAndAxisLinePadding(20.dp)
                    .labelData { i ->
                        val yMin = 0
                        val yMax = pointsData.maxOf {it.y}
                        val yScale = (yMax - yMin) / steps
                        ((i*yScale) + yMin).formatToSinglePrecision()
                    }.build()

                val data = BubbleChartData(
                    DataUtils.getBubbleChartDataWithSolidStyle(pointsData),
                    xAxisData = xAxisData,
                    yAxisData = yAxisData,
                    gridLines = GridLines()
                )

                BubbleChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp),
                    bubbleChartData = data
                )
            }

            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .clickable {
                        navController.navigateUp()
                    },
                textAlign = TextAlign.Center,
                text = "Takaisin päävalikkoon",
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
        }
    }
}