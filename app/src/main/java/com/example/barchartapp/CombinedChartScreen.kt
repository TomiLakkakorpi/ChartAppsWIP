package com.example.barchartapp

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.components.Legends
import co.yml.charts.common.model.LegendLabel
import co.yml.charts.common.model.LegendsConfig
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarPlotData
import co.yml.charts.ui.barchart.models.BarStyle
import co.yml.charts.ui.barchart.models.GroupBar
import co.yml.charts.ui.combinedchart.CombinedChart
import co.yml.charts.ui.combinedchart.model.CombinedChartData
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp

import com.example.barchartapp.ui.theme.color1
import com.example.barchartapp.ui.theme.color2
import com.example.barchartapp.ui.theme.color9

@Composable
fun CombinedChartScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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

            Text(
                modifier = Modifier.padding(10.dp, 50.dp, 10.dp, 10.dp),
                text = "Lämpötila ja sademäärä ennuste 23.5. - 1.6.2025"
            )
            DrawCombinedChart()
        }
    }
}

@Composable
fun DrawCombinedChart() {

    val maxRange = 20
    val yStepSize = 10

    // Data 10pv sääennuste Oulu Pe 23.5. - Su 1.6.
    val lineData = arrayListOf(
        Point(0f, 1.9f),
        Point(1f, 1.7f),
        Point(2f, 0.8f),
        Point(3f, 3.3f),
        Point(4f, 2.8f),
        Point(5f, 3.7f),
        Point(6f, 3.7f),
        Point(7f, 9.0f),
        Point(8f, 7.2f),
        Point(9f, 0.2f),
    )

    val dateList = arrayListOf(
        "23.5.",
        "24.5.",
        "25.5.",
        "26.5.",
        "27.5.",
        "28.5.",
        "29.5.",
        "30.5.",
        "31.5.",
        "1.6.",
        "", //Empty element at the end to prevent index out of bounds error
    )

    val groupBarData = arrayListOf(
        GroupBar(label = "23.5.", barList = arrayListOf(
            BarData(point = Point(1F, 11f)),
            BarData(point = Point(2F, 18f))
        )),

        GroupBar(label = "24.5.", barList = arrayListOf(
            BarData(point = Point(1F, 5f)),
            BarData(point = Point(2F, 16f))
        )),

        GroupBar(label = "25.5.", barList = arrayListOf(
            BarData(point = Point(1F, 8f)),
            BarData(point = Point(2F, 11f))
        )),

        GroupBar(label = "26.5.", barList = arrayListOf(
            BarData(point = Point(1F, 7f)),
            BarData(point = Point(2F, 15f))
        )),

        GroupBar(label = "27.5.", barList = arrayListOf(
            BarData(point = Point(1F, 9f)),
            BarData(point = Point(2F, 15f))
        )),

        GroupBar(label = "28.5.", barList = arrayListOf(
            BarData(point = Point(1F, 9f)),
            BarData(point = Point(2F, 14f))
        )),

        GroupBar(label = "29.5.", barList = arrayListOf(
            BarData(point = Point(1F, 9f)),
            BarData(point = Point(2F, 16f))
        )),

        GroupBar(label = "30.5.", barList = arrayListOf(
            BarData(point = Point(1F, 10f)),
            BarData(point = Point(2F, 16f))
        )),

        GroupBar(label = "31.5.", barList = arrayListOf(
            BarData(point = Point(1F, 8f)),
            BarData(point = Point(2F, 14f))
        )),

        GroupBar(label = "1.6.", barList = arrayListOf(
            BarData(point = Point(1F, 8f)),
            BarData(point = Point(2F, 14f))
        )),
    )

    val xAxisData = AxisData.Builder()
        .axisStepSize(20.dp)
        .bottomPadding(5.dp)
        //.labelData { index -> index.toString() }
        .labelData { index -> dateList[index].toString() }
        .build()

    val testvalue = groupBarData.size
    Log.d("Test", "$testvalue")

    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() + " °C" }
        .build()

    val linePlotData = LinePlotData(
        lines = listOf(
            Line(
                dataPoints = lineData,
                lineStyle = LineStyle(color = color9),
                intersectionPoint = IntersectionPoint(),
                selectionHighlightPoint = SelectionHighlightPoint(),
                selectionHighlightPopUp = SelectionHighlightPopUp()
            )
        )
    )

    val legendsConfig = LegendsConfig(
        legendLabelList = arrayListOf(
            LegendLabel(
                color = color1,
                name = "Alin lämpötila °C"
            ),
            LegendLabel(
                color = color2,
                name = "Ylin lämpötila °C"
            ),
            LegendLabel(
                color = color9,
                name = "Sademäärä mm"
            )
        ),
        gridColumnCount = 2
    )

    val colorPaletteList = listOf(color1, color2,)

    val barPlotData = BarPlotData(
        groupBarList = groupBarData,
        barStyle = BarStyle(barWidth = 35.dp),
        barColorPaletteList = colorPaletteList
    )

    val combinedChartData = CombinedChartData(
        combinedPlotDataList = listOf(barPlotData, linePlotData),
        xAxisData = xAxisData,
        yAxisData = yAxisData
    )

    Column(
        Modifier
            .height(500.dp)
    ) {
        CombinedChart(
            modifier = Modifier
                .height(400.dp),
            combinedChartData = combinedChartData
        )
        Legends(
            legendsConfig = legendsConfig
        )
    }
}