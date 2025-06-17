package com.example.barchartapp

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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

//YCharts Importit
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

//MathParser Importit
import org.mariuszgromada.math.mxparser.Argument
import org.mariuszgromada.math.mxparser.Expression

var Calculator2lineChartList = mutableListOf<Point>()

@Composable
fun GraphingCalculatorScreen2(navController: NavController) {
    var text by remember { mutableStateOf("") }
    var formula by remember {mutableStateOf("")}
    var e: Expression
    var x: Argument
    var y: Argument
    var xValue by remember {mutableFloatStateOf(0.5f)}
    var yValue = 0f
    var chart1Drawn by remember {mutableStateOf(false)}
    var xStart by remember { mutableFloatStateOf(-5.0f) }
    var xEnd by remember { mutableFloatStateOf(5.0f) }
    var xIncrement by remember { mutableFloatStateOf(0.1f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column()
        {
            val context = LocalContext.current
            var steps: Int

            if (Calculator2lineChartList.size >= 10) {
                steps = 10
            } else {
                steps = Calculator2lineChartList.size
            }

            val xAxisData = AxisData.Builder()
                .axisStepSize(30.dp)
                .startDrawPadding(48.dp)
                .steps(Calculator2lineChartList.size - 1)
                .labelAndAxisLinePadding(25.dp)
                .labelData { i ->
                    val xMin = Calculator2lineChartList.minOf { it.x }
                    val xMax = Calculator2lineChartList.maxOf { it.x }
                    val xScale = (xMax - xMin) / steps
                    ((i * xScale) + xMin).formatToSinglePrecision()
                }.build()

            val yAxisData = AxisData.Builder()
                .steps(steps)
                .labelAndAxisLinePadding(20.dp)
                .labelData { i ->
                    val yMin = Calculator2lineChartList.minOf { it.y }
                    val yMax = Calculator2lineChartList.maxOf { it.y }
                    val yScale = (yMax - yMin) / steps
                    ((i * yScale) + yMin).formatToSinglePrecision()
                }.build()

            val data = WaveChartData(
                wavePlotData = WavePlotData(
                    lines = listOf(
                        Wave(
                            dataPoints = Calculator2lineChartList,
                            waveStyle = LineStyle(color = Color.Black),
                            selectionHighlightPoint = SelectionHighlightPoint(),
                            shadowUnderLine = ShadowUnderLine(),
                            selectionHighlightPopUp = SelectionHighlightPopUp()
                        ),
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
                    .width(370.dp)
                    .height(300.dp)
            ) {
                if(Calculator2lineChartList.isNotEmpty()){
                    WaveChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp),
                        waveChartData = data
                    )
                }
            }

            TextField(
                modifier = Modifier.width(200.dp),
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
                label = {
                    Text(text = "Kirjoita kaava")
                },
            )

            Row() {
                Button(
                    modifier = Modifier.padding(0.dp, 0.dp, 20.dp, 0.dp),
                    onClick = {
                        formula = text
                        if(formula.isNotEmpty()){
                            if(!chart1Drawn) {
                                while(xValue <= xEnd) {
                                    x = Argument("x=$xValue")
                                    y = Argument(formula, x)
                                    e = Expression("y", y)
                                    yValue = e.calculate().toFloat()
                                    Calculator2lineChartList.add(Point(xValue, yValue, ""))
                                    xValue = xValue + xIncrement
                                }
                                chart1Drawn = true
                            } else {
                                Toast.makeText(context, "Kaava on jo piirretty", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "Syötä kaava!", Toast.LENGTH_SHORT).show()
                        }
                        text = " "
                        text = ""
                    }
                ) {
                    Text("Piirrä kaavio")
                }

                Button(
                    onClick = {
                        if (Calculator2lineChartList.isNotEmpty()) {
                            while(Calculator2lineChartList.isNotEmpty()) {
                                Calculator2lineChartList.removeAt(Calculator2lineChartList.size -1)
                            }
                            xValue = xStart
                            chart1Drawn = false
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
            }

            Row() {
                Column() {
                    TextField(
                        modifier = Modifier
                            .padding(5.dp)
                            .width(150.dp),
                        value = xStart.toString(),
                        onValueChange = { newText ->
                            xStart = newText.toFloat()
                        },
                        label = {
                            Text(text = "Syötä x Lähtöarvo")
                        },
                    )
                    TextField(
                        modifier = Modifier
                            .padding(5.dp)
                            .width(150.dp),
                        value = xEnd.toString(),
                        onValueChange = { newText ->
                            xEnd = newText.toFloat()
                        },
                        label = {
                            Text(text = "Syötä x Loppuarvo")
                        },
                    )
                }
                Column() {
                    TextField(
                        modifier = Modifier
                            .padding(5.dp)
                            .width(150.dp),
                        value = xIncrement.toString(),
                        onValueChange = { newText ->
                            xIncrement = newText.toFloat()
                        },
                        label = {
                            Text(text = "Syötä x lisäys")
                        },
                    )
                    Button(
                        shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                        modifier = Modifier.padding(5.dp),
                        onClick = {
                            xValue = xStart
                            Toast.makeText(context, "X arvot asetettu!", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Text("Aseta X arvot")
                    }
                }
            }

            Box(
                modifier = Modifier.padding(0.dp, 50.dp, 0.dp, 0.dp),
                contentAlignment = Alignment.Center
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