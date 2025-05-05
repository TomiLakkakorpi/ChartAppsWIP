package com.example.barchartapp

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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

var index = 0F
var list = mutableListOf<Point>()
var listSize = 0
var isListInitialized = false

@Composable
fun LineChartScreen(navController: NavController) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box() {
                /* val pointsData: List<Point> =
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
                    ) */
            }

            if(!isListInitialized) {
                list.add(Point(0f, 0f))
                isListInitialized = true
            }

            val steps = list.size

            val xAxisData = AxisData.Builder()
                .axisStepSize(35.dp)
                .topPadding(105.dp)
                .steps(list.size - 1)
                .labelData { i -> list[i].x.toInt().toString() }
                .labelAndAxisLinePadding(15.dp)
                .build()

            val yAxisData = AxisData.Builder()
                .steps(steps)
                .labelAndAxisLinePadding(20.dp)
                .labelData { i ->
                    //val yMax = 100.0f
                    //val yMin = 0.0f
                    val yMin = list.minOf { it.y }
                    val yMax = list.maxOf { it.y }
                    val yScale = (yMax - yMin) / steps
                    ((i * yScale) + yMin).formatToSinglePrecision()
                }.build()

            val data = LineChartData(
                linePlotData = LinePlotData(
                    lines = listOf(
                        Line(
                            dataPoints = list,
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

            var text by remember {
                mutableStateOf("")
            }

            LineChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                lineChartData = data
            )
            Log.d("ImeAction", "LineChart updated")

            TextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
                label = {
                    Text(text = "Syötä arvo")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if(text.isNotEmpty()){
                            if(index <= 9) {
                                index++
                                list.add(Point(index, text.toFloat(), ""))
                                Log.d("ImeAction", "Value $text added to list in position $index")
                                text = ""
                            } else {
                                Toast.makeText(context, "Maksimimäärä saavutettu", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "Syötä arvo!", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            )

            Text(
                modifier = Modifier.clickable {

                },
                text = "Tyhjennä taulukko",
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )

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
