package com.example.barchartapp

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import co.yml.charts.ui.wavechart.WaveChart
import co.yml.charts.ui.wavechart.model.Wave
import co.yml.charts.ui.wavechart.model.WaveChartData
import co.yml.charts.ui.wavechart.model.WavePlotData
import org.mariuszgromada.math.mxparser.Argument
import org.mariuszgromada.math.mxparser.Expression

var calculator1lineChartListIndex = 0F
var calculator1lineChartList = mutableListOf<Point>()

@Composable
fun GraphingCalculatorScreen1(navController: NavController) {

    var text by remember { mutableStateOf("") }

    var formula by remember {mutableStateOf("")}

    var e: Expression
    var x: Argument
    var y: Argument

    var xValue = -5f
    var yValue = 0f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column()
        {
            val context = LocalContext.current
            var steps: Int

            if (calculator1lineChartList.size >= 10) {
                steps = 10
            } else {
                steps = calculator1lineChartList.size
            }

            val xAxisData = AxisData.Builder()
                .axisStepSize(30.dp)
                .startDrawPadding(48.dp)
                .steps(calculator1lineChartList.size - 1)
                .labelAndAxisLinePadding(30.dp)
                .labelData { i ->
                    val xMin = calculator1lineChartList.minOf { it.x }
                    val xMax = calculator1lineChartList.maxOf { it.x }
                    val xScale = (xMax - xMin) / steps
                    ((i * xScale) + xMin).formatToSinglePrecision()
                }.build()

            val yAxisData = AxisData.Builder()
                .steps(steps)
                .labelAndAxisLinePadding(20.dp)
                .labelData { i ->
                    val yMin = calculator1lineChartList.minOf { it.y }
                    val yMax = calculator1lineChartList.maxOf { it.y }
                    val yScale = (yMax - yMin) / steps
                    ((i * yScale) + yMin).formatToSinglePrecision()
                }.build()

            val data = WaveChartData(
                wavePlotData = WavePlotData(
                    lines = listOf(
                        Wave(
                            dataPoints = calculator1lineChartList,
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

            Text(
                modifier = Modifier.padding(10.dp),
                text = "Piirretty Kaava: $formula"
            )

            Box(
                modifier = Modifier
                    .width(400.dp)
                    .height(400.dp)
            ) {
                Log.d("FormulaTest", "Checking if chart can be drawed")

                if(calculator1lineChartList.isNotEmpty()){
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

            Row() {
                Button(
                    modifier = Modifier.padding(0.dp, 0.dp, 20.dp, 0.dp),
                    onClick = {
                        if(text.isNotEmpty()){
                            while(xValue <= 5) {
                                x = Argument("x=$xValue")
                                y = Argument(text, x)
                                e = Expression("y", y)
                                yValue = e.calculate().toFloat()

                                calculator1lineChartList.add(Point(xValue, yValue, ""))

                                Log.d("FormulaTest", "Point added to list is: x: $xValue, y: $yValue")

                                xValue = floatAddition(xValue, 0.1f)
                            }
                        } else {
                            Toast.makeText(context, "Syötä kaava!", Toast.LENGTH_SHORT).show()
                        }

                        formula = text
                        Log.d("FormulaTest", "Formula is $formula")

                        text = " "
                        text = ""
                    }
                ) {
                    Text("Piirrä kaavio")
                }
            }

            Button(
                onClick = {
                    if (calculator1lineChartList.isNotEmpty()) {
                        while(calculator1lineChartList.isNotEmpty()) {
                            calculator1lineChartList.removeAt(calculator1lineChartList.size -1)
                        }

                        calculator1lineChartListIndex = 0f

                        text = " "
                        text = ""
                        formula = ""
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

private fun floatAddition(numA: Float, numB: Float): Float {
    var value = numA + numB
    return value.formatToSinglePrecision().toFloat()
}