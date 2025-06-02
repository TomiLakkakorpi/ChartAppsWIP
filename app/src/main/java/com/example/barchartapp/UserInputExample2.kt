package com.example.barchartapp

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.extensions.formatToSinglePrecision
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

import kotlin.math.asin

var lineChartListIndex3 = 0F
var lineChartList3 = mutableListOf<Point>(
    Point(-2f, -1.75f),
    Point(-1.5f, -1.25f),
    Point(-1f, -0.5f),
    Point(-0.5f, -0.25f),
    Point(0f, 0f),
    Point(0.5f, 0.25f),
    Point(1f, 0.5f),
    Point(1.5f, 1.25f),
    Point(2f, 1.75f)
)

@Composable
fun UserInputExample2(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            val context = LocalContext.current

            var text by remember {
                mutableStateOf("")
            }

            Box(
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    //verticalArrangement = Arrangement.Center
                ) {
                    var steps: Int

                    if (lineChartList3.size >= 10) {
                        steps = 10
                    } else {
                        steps = lineChartList3.size
                    }

                    val xAxisData = AxisData.Builder()
                        .axisStepSize(35.dp)
                        .topPadding(105.dp)
                        .steps(lineChartList3.size - 1)
                        .labelData { i -> lineChartList3[i].x.toString() }
                        .labelAndAxisLinePadding(15.dp)
                        .build()

                    val yAxisData = AxisData.Builder()
                        .steps(steps)
                        .labelAndAxisLinePadding(25.dp)
                        .labelData { i ->
                            val yMin = lineChartList3.minOf { it.y }
                            val yMax = lineChartList3.maxOf { it.y }
                            val yScale = (yMax - yMin) / steps
                            ((i * yScale) + yMin).formatToSinglePrecision()
                        }.build()

                    val data = LineChartData(
                        linePlotData = LinePlotData(
                            lines = listOf(
                                Line(
                                    dataPoints = lineChartList3,
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
                        if(lineChartList3.isNotEmpty()){
                            LineChart(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp),
                                lineChartData = data
                            )
                        }
                    }

                    TextField(
                        value = text,
                        onValueChange = { newText ->
                            text = newText
                        },
                        label = {
                            Text(text = "Syötä kaava")
                        },
                    )

                    Button(
                        onClick = {
                            if(text.isNotEmpty()){
                                if(checkIfValidValue2(text)) {
                                    lineChartList3.add(Point(lineChartListIndex3, text.toFloat(), ""))
                                    text = ""
                                    lineChartListIndex3++
                                } else {
                                    Toast.makeText(context, "Syöttämäsi arvoa ei voida hyväksyä! Syötä arvo muodossa 1.1", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Toast.makeText(context, "Syötä arvo!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    ) {
                        Text("Lisää arvo kaavioon")
                    }

                    Button(
                        onClick = {
                            if (lineChartList3.isNotEmpty()) {
                                while(lineChartList3.isNotEmpty()) {
                                    lineChartList3.removeAt(lineChartList3.size -1)
                                }

                                lineChartListIndex3 = 0f

                                text = " "
                                text = ""
                            } else {
                                Toast.makeText(context, "Taulukko on jo tyhjä!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    ) {
                        Text("Tyhjennä taulukko")
                    }

                    Button(
                        shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Green,
                            contentColor = Color.Black
                        ),
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
}

fun checkIfValidValue2(input: String): Boolean {
    val regex = Regex("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)")
    return input.matches(regex)
}