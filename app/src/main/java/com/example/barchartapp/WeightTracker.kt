package com.example.barchartapp

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

var weightTrackerIndex = 0F
var weightTrackerList = mutableListOf<Point>()
var isWeightTrackerListInitialized = false
var doesWeightTrackerListHaveData = false

@Composable
fun WeightTrackerScreen(navController: NavController) {
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
            if(!isWeightTrackerListInitialized) {
                weightTrackerList.add(Point(0f, 0f))
                isWeightTrackerListInitialized = true

                text = " "
                text = ""
            }

            val axisSteps = if(weightTrackerList.size < 10) {
                weightTrackerList.size
            } else {
                10
            }

            val xAxisData = AxisData.Builder()
                .axisStepSize(35.dp)
                .topPadding(105.dp)
                .labelAndAxisLinePadding(20.dp)
                .steps(weightTrackerList.size -1)
                .labelData { i -> weightTrackerList[i].x.toInt().toString() }
                .labelAndAxisLinePadding(15.dp)
                .build()

            val yAxisData = AxisData.Builder()
                .steps(axisSteps)
                //.labelAndAxisLinePadding(20.dp)
                //.axisLabelFontSize(20.sp)
                //.startPadding((-10).dp)
                //.axisLabelFontSize(15.sp)
                .labelAndAxisLinePadding(10.dp)
                .labelData { i ->
                    val yMin = weightTrackerList.minOf { it.y }
                    val yMax = weightTrackerList.maxOf { it.y }
                    val yScale = (yMax - yMin) / axisSteps
                    ((i * yScale) + yMin).formatToSinglePrecision()
                }.build()

            val data = LineChartData(
                linePlotData = LinePlotData(
                    lines = listOf(
                        Line(
                            dataPoints = weightTrackerList,
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

                            if(weightTrackerIndex.toInt() == 0) {
                                weightTrackerList[weightTrackerIndex.toInt()] = Point(weightTrackerIndex, text.toFloat())
                            } else {
                                weightTrackerList.add(Point(weightTrackerIndex, text.toFloat(), ""))
                            }

                            Log.d("ImeAction", "Value $text added to list in position $weightTrackerList")
                            text = ""
                            weightTrackerIndex++

                            if(doesWeightTrackerListHaveData == false) {
                                doesWeightTrackerListHaveData = true
                            }

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
                        navController.navigateUp()
                    },
                text = "Takaisin päävalikkoon",
                fontSize = 20.sp
            )
        }
    }
}
