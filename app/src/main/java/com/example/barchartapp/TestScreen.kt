package com.example.barchartapp

import android.util.Log
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
import co.yml.charts.ui.wavechart.WaveChart
import co.yml.charts.ui.wavechart.model.Wave
import co.yml.charts.ui.wavechart.model.WaveChartData
import co.yml.charts.ui.wavechart.model.WaveFillColor
import co.yml.charts.ui.wavechart.model.WavePlotData
import org.mariuszgromada.math.mxparser.Argument
import org.mariuszgromada.math.mxparser.Expression

var lineChartListIndex4 = 0F
var lineChartList4 = mutableListOf<Point>()

@Composable
fun TestScreen(navController: NavController) {

    var text by remember { mutableStateOf("") }
    var e: Expression
    var x: Argument
    var y: Argument
    var index = 0
    var xValue = -2.5f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column()
        {
            val context = LocalContext.current
            var steps: Int

            if (lineChartList4.size >= 10) {
                steps = 10
            } else {
                steps = lineChartList4.size
            }

            val xAxisData = AxisData.Builder()
                .axisStepSize(30.dp)
                .startDrawPadding(48.dp)
                .steps(lineChartList4.size - 1)
                .labelData { i -> i.toFloat().toString() }
                .labelAndAxisLinePadding(15.dp)
                .build()

            val yAxisData = AxisData.Builder()
                .steps(steps)
                .labelAndAxisLinePadding(20.dp)
                .labelData { i ->
                    val yMin = lineChartList4.minOf { it.y }
                    val yMax = lineChartList4.maxOf { it.y }
                    val yScale = (yMax - yMin) / steps
                    ((i * yScale) + yMin).formatToSinglePrecision()
                }.build()

            val data = WaveChartData(
                wavePlotData = WavePlotData(
                    lines = listOf(
                        Wave(
                            dataPoints = lineChartList4,
                            waveStyle = LineStyle(color = Color.Black),
                            selectionHighlightPoint = SelectionHighlightPoint(),
                            shadowUnderLine = ShadowUnderLine(),
                            selectionHighlightPopUp = SelectionHighlightPopUp()
                            //waveFillColor = WaveFillColor(topColor = Color.Green, bottomColor = Color.Red),
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
                Log.d("FormulaTest", "Checking if chart can be drawed")
                if(lineChartList4.isNotEmpty()){
                    Log.d("FormulaTest", "Drawing Chart")
                    WaveChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp),
                        waveChartData = data
                    )
                }
            }

            TextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
                label = {
                    Text(text = "Kirjoita kaavio")
                },
            )

            Text("Käytetty kaava: $text")

            Button(
                modifier = Modifier.padding(0.dp, 0.dp, 20.dp, 0.dp),
                onClick = {
                    if(text.isNotEmpty()){
                        while(index <= 21) {
                            x = Argument("x=$xValue")
                            y = Argument(text, x)
                            e = Expression("y", y)

                            lineChartList4.add(Point(xValue, e.calculate().toFloat(), ""))
                            index++
                            xValue = floatAddition(xValue, 0.25f)

                            Log.d("FormulaTest", "Calculation value is: " + e.calculate().toString())
                            Log.d("FormulaTest", "xValue is: $xValue")
                            Log.d("FormulaTest", "List size is: ${lineChartList4.size}")
                            Log.d("FormulaTest", "Formula is $text")
                            Log.d("FormulaTest", "-------------------------------------")
                        }
                    } else {
                        Toast.makeText(context, "Placeholder", Toast.LENGTH_SHORT).show()
                    }

                    text = " "
                    text = ""
                }
            ) {
                Text("Syötä kaavio")
            }

            Button(
                onClick = {
                    if (lineChartList4.isNotEmpty()) {
                        while(lineChartList4.isNotEmpty()) {
                            lineChartList4.removeAt(lineChartList4.size -1)
                        }

                        lineChartListIndex4 = 0f

                        text = " "
                        text = ""
                    } else {
                        Toast.makeText(context, "Taulukko on jo tyhjä!", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text("Tyhjennä taulukko")
            }

            Box(
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
                //contentAlignment = Alignment.Center
            ) {
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

fun floatAddition(numA: Float, numB: Float): Float {
    return numA + numB
}