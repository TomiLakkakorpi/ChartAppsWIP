package com.example.barchartapp

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
var isLineChartListInitialized = false
var doesLineChartListHaveData = false

@Composable
fun LineChartScreen(navController: NavController) {
    val context = LocalContext.current

    var text by remember {
        mutableStateOf("")
    }

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

            if(!isLineChartListInitialized) {
                lineChartList.add(Point(0f, 0f))
                lineChartList.add(Point(1f, 0f))
                lineChartList.add(Point(2f, 0f))
                lineChartList.add(Point(3f, 0f))
                lineChartList.add(Point(4f, 0f))
                lineChartList.add(Point(5f, 0f))
                lineChartList.add(Point(6f, 0f))
                lineChartList.add(Point(7f, 0f))
                lineChartList.add(Point(8f, 0f))
                lineChartList.add(Point(9f, 0f))
                isLineChartListInitialized = true

                text = " "
                text = ""
            }

            val steps = lineChartList.size

            val xAxisData = AxisData.Builder()
                .axisStepSize(35.dp)
                .topPadding(105.dp)
                .steps(lineChartList.size - 1)
                .labelData { i -> lineChartList[i].x.toInt().toString() }
                .labelAndAxisLinePadding(15.dp)
                .build()

            val yAxisData = AxisData.Builder()
                .steps(steps)
                .labelAndAxisLinePadding(20.dp)
                .labelData { i ->
                    //val yMax = 100.0f
                    //val yMin = 0.0f
                    val yMin = lineChartList.minOf { it.y }
                    val yMax = lineChartList.maxOf { it.y }
                    val yScale = (yMax - yMin) / steps
                    ((i * yScale) + yMin).formatToSinglePrecision()
                }.build()

            val data = LineChartData(
                linePlotData = LinePlotData(
                    lines = listOf(
                        Line(
                            dataPoints = lineChartList,
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

            Box(
                modifier = Modifier
                    .width(370.dp)
                    .height(300.dp)

            ) {
                LineChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    lineChartData = data
                )
                Log.d("ImeAction", "LineChart updated")
            }

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
                            //if(index <= 9) {
                                lineChartList[lineChartListIndex.toInt()] = Point(lineChartListIndex, text.toFloat())
                                //list.add(Point(index, text.toFloat(), ""))
                                Log.d("ImeAction", "Value $text added to list in position $lineChartListIndex")
                                text = ""
                                lineChartListIndex++

                                if(doesLineChartListHaveData == false) {
                                    doesLineChartListHaveData = true
                                }
                            //} else {
                            //    Toast.makeText(context, "Maksimimäärä saavutettu", Toast.LENGTH_SHORT).show()
                            //}
                        } else {
                            Toast.makeText(context, "Syötä arvo!", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            )

            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .clickable {
                        if (doesLineChartListHaveData == true) {

                            /* WIP
                            val it = list.listIterator()

                            if(it.hasNext()) {
                                list[index.toInt()] = Point(index, 0f)
                            }

                            val it = list.listIterator()
                            for (e in it) {
                                if (e.length >  1) {
                                    list[index.toInt()] = Point(index, text.toFloat())
                                }
                            } */

                            lineChartList[0] = Point(0f, 0f)
                            lineChartList[1] = Point(1f, 0f)
                            lineChartList[2] = Point(2f, 0f)
                            lineChartList[3] = Point(3f, 0f)
                            lineChartList[4] = Point(4f, 0f)
                            lineChartList[5] = Point(5f, 0f)
                            lineChartList[6] = Point(6f, 0f)
                            lineChartList[7] = Point(7f, 0f)
                            lineChartList[8] = Point(8f, 0f)
                            lineChartList[9] = Point(9f, 0f)


                            lineChartListIndex = 0f

                            Log.d("ImeAction", "List cleared")

                            doesLineChartListHaveData = false

                            text = " "
                            text = ""
                        }
                    },
                text = "Tyhjennä taulukko",
                fontSize = 20.sp
            )

            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .clickable {
                        navController.navigateUp()
                    },
                text = "Takaisin päävalikkoon",
                fontSize = 20.sp
            )
        }
    }
}
