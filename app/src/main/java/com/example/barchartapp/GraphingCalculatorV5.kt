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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import kotlin.math.abs

var Calculator5squareXIndex = 0
var Calculator5squareYIndex = 0

var Calculator5squareRootXIndex = 0
var Calculator5squareRootYIndex = 0

var Calculator5lineChartList = mutableListOf<Point>()

@Composable
fun GraphingCalculatorScreen5(navController: NavController) {

    var text by remember { mutableStateOf("") }
    var xStart by remember { mutableFloatStateOf(-5.0f) }
    var xEnd by remember { mutableFloatStateOf(5.0f) }
    var xIncrement by remember { mutableFloatStateOf(0.1f) }
    var xValue by remember {mutableFloatStateOf(-5f)}

    var e: Expression
    var x: Argument
    var y: Argument

    var formula by remember {mutableStateOf("")}

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column()
        {
            val context = LocalContext.current
            var steps: Int

            if (Calculator5lineChartList.size >= 10) {
                steps = 10
            } else {
                steps = Calculator5lineChartList.size
            }

            val xAxisData = AxisData.Builder()
                .axisStepSize(30.dp)
                .startDrawPadding(48.dp)
                .steps(Calculator5lineChartList.size - 1)
                .labelAndAxisLinePadding(25.dp)
                .labelData { i ->
                    val xMin = Calculator5lineChartList.minOf { it.x }
                    val xMax = Calculator5lineChartList.maxOf { it.x }
                    val xScale = (xMax - xMin) / steps
                    ((i * xScale) + xMin).formatToSinglePrecision()
                }.build()

            val yAxisData = AxisData.Builder()
                .steps(steps)
                .labelAndAxisLinePadding(20.dp)
                .labelData { i ->
                    val yMin = Calculator5lineChartList.minOf { it.y }
                    val yMax = Calculator5lineChartList.maxOf { it.y }
                    val yScale = (yMax - yMin) / steps
                    ((i * yScale) + yMin).formatToSinglePrecision()
                }.build()

            val data = WaveChartData(
                wavePlotData = WavePlotData(
                    lines = listOf(
                        Wave(
                            dataPoints = Calculator5lineChartList,
                            waveStyle = LineStyle(color = Color.Black),
                            selectionHighlightPoint = SelectionHighlightPoint(),
                            shadowUnderLine = ShadowUnderLine(),
                            selectionHighlightPopUp = SelectionHighlightPopUp()
                            //waveFillColor = WaveFillColor(topColor = Color.Green, bottomColor = Color.Red),
                        ),
                    )
                ),
                xAxisData = xAxisData,
                yAxisData = yAxisData,
                gridLines = GridLines()
            )

            Text(
                modifier = Modifier.padding(10.dp, 20.dp, 0.dp, 0.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = "Graafinen laskin 5: Datan muunnos"
            )

            Text(
                modifier = Modifier.padding(10.dp, 5.dp, 0.dp, 0.dp),
                text = "Piirretty Kaava: $formula"
            )

            Box(
                modifier = Modifier
                    .width(370.dp)
                    .height(300.dp)
            ) {
                Log.d("FormulaTest", "Checking if chart can be drawed")

                if(Calculator5lineChartList.isNotEmpty()){
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
                    modifier = Modifier.padding(0.dp, 10.dp, 20.dp, 0.dp),
                    onClick = {
                        formula = text
                        if(formula.isNotEmpty()){
                            while(xValue <= xEnd) {
                                x = Argument("x=$xValue")
                                y = Argument(formula, x)
                                e = Expression("y", y)

                                Calculator5lineChartList.add(Point(xValue, e.calculate().toFloat(), ""))

                                Log.d("FormulaTest", "Point added to list is: x: $xValue, y: " + e.calculate().toString())
                                Log.d("FormulaTest", "Point added is in position ${Calculator5lineChartList.size}")

                                xValue = floatAddition(xValue, xIncrement)
                            }
                        } else {
                            Toast.makeText(context, "Syötä kaava!", Toast.LENGTH_SHORT).show()
                        }

                        xValue = xStart

                        text = " "
                        text = ""
                    }
                ) {
                    Text("Piirrä kaava")
                }

                Button(
                    modifier = Modifier.padding(0.dp, 10.dp, 20.dp, 0.dp),
                    onClick = {
                        if (Calculator5lineChartList.isNotEmpty()) {
                            while(Calculator5lineChartList.isNotEmpty()) {
                                Calculator5lineChartList.removeAt(Calculator5lineChartList.size -1)
                            }

                            text = " "
                            text = ""
                            formula = ""

                            Calculator5squareXIndex = 0
                            Calculator5squareYIndex = 0
                        } else {
                            Toast.makeText(context, "Taulukko on jo tyhjä!", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text("Tyhjennä taulukko")
                }
            }

            Row() {
                Button(
                    modifier = Modifier.padding(5.dp, 5.dp, 5.dp, 5.dp),
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White
                    ),
                    onClick = {
                        if(Calculator5lineChartList.isEmpty()) {
                            Toast.makeText(context, "Lista on tyhjä, syötä ensin kaava!", Toast.LENGTH_SHORT).show()
                        } else if(Calculator5squareXIndex == Calculator5lineChartList.size) {
                            Toast.makeText(context, "x arvot on jo kerran muunnettu", Toast.LENGTH_SHORT).show()
                        } else {
                            while(Calculator5squareXIndex < Calculator5lineChartList.size) {
                                val y = Calculator5lineChartList[Calculator5squareXIndex].y
                                val x = floatSquared(Calculator5lineChartList[Calculator5squareXIndex].x)
                                Calculator5lineChartList[Calculator5squareXIndex] = Point(x,y)
                                Calculator5squareXIndex++

                                Log.d("modification", "Arvo $Calculator5squareXIndex päivitetty x: $x ja y: $y arvoilla")
                            }

                            text = " "
                            text = ""
                            Calculator5squareRootXIndex = 0
                        }
                    }
                ) {
                    Text("x²")
                }

                Button(
                    modifier = Modifier.padding(5.dp, 5.dp, 5.dp, 5.dp),
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White
                    ),
                    onClick = {
                        if(Calculator5lineChartList.isEmpty()) {
                            Toast.makeText(context, "Lista on tyhjä, syötä ensin kaava!", Toast.LENGTH_SHORT).show()
                        } else if(Calculator5squareYIndex == Calculator5lineChartList.size){
                            Toast.makeText(context, "y arvot on jo kerran muunnettu", Toast.LENGTH_SHORT).show()
                        } else {
                            while(Calculator5squareYIndex < Calculator5lineChartList.size) {
                                val x = Calculator5lineChartList[Calculator5squareYIndex].x
                                val y = floatSquared(Calculator5lineChartList[Calculator5squareYIndex].y)
                                Calculator5lineChartList[Calculator5squareYIndex] = Point(x,y)
                                Calculator5squareYIndex++

                                Log.d("modification", "Arvo $Calculator5squareYIndex päivitetty x: $x ja y: $y arvoilla")
                            }
                            text = " "
                            text = ""
                            Calculator5squareRootYIndex = 0
                        }
                    }
                ) {
                    Text("y²")
                }

                Button(
                    modifier = Modifier.padding(5.dp, 5.dp, 5.dp, 5.dp),
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White
                    ),
                    onClick = {
                        if(Calculator5lineChartList.isEmpty()) {
                            Toast.makeText(context, "Lista on tyhjä, syötä ensin kaava!", Toast.LENGTH_SHORT).show()
                        } else if(Calculator5squareRootXIndex == Calculator5lineChartList.size) {
                            Toast.makeText(context, "x arvot on jo kerran muunnettu", Toast.LENGTH_SHORT).show()
                        } else {
                            while(Calculator5squareRootXIndex < Calculator5lineChartList.size) {
                                val y = Calculator5lineChartList[Calculator5squareRootXIndex].y
                                val x = floatSquareRoot(Calculator5lineChartList[Calculator5squareRootXIndex].x)
                                Calculator5lineChartList[Calculator5squareRootXIndex] = Point(x,y)
                                Calculator5squareRootXIndex++

                                Log.d("modification", "Arvo $Calculator5squareRootXIndex päivitetty x: $x ja y: $y arvoilla")
                            }

                            text = " "
                            text = ""
                            Calculator5squareXIndex = 0
                        }
                    }
                ) {
                    Text("²√x")
                }

                Button(
                    modifier = Modifier.padding(5.dp, 5.dp, 5.dp, 5.dp),
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White
                    ),
                    onClick = {
                        if(Calculator5lineChartList.isEmpty()) {
                            Toast.makeText(context, "Lista on tyhjä, syötä ensin kaava!", Toast.LENGTH_SHORT).show()
                        } else if(Calculator5squareRootYIndex == Calculator5lineChartList.size) {
                            Toast.makeText(context, "y arvot on jo kerran muunnettu", Toast.LENGTH_SHORT).show()
                        } else {
                            while(Calculator5squareRootYIndex < Calculator5lineChartList.size) {
                                val y = Calculator5lineChartList[Calculator5squareRootYIndex].x
                                val x = floatSquareRoot(Calculator5lineChartList[Calculator5squareRootYIndex].y)
                                Calculator5lineChartList[Calculator5squareRootYIndex] = Point(x,y)
                                Calculator5squareRootYIndex++

                                Log.d("modification", "Arvo $Calculator5squareRootYIndex päivitetty x: $x ja y: $y arvoilla")
                            }

                            text = " "
                            text = ""
                            Calculator5squareYIndex = 0
                        }
                    }
                ) {
                    Text("²√y")
                }
            }

            Box(
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
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

private fun floatAddition(numA: Float, numB: Float): Float {
    var value = numA + numB
    return value.formatToSinglePrecision().toFloat()
}

private fun floatSquared(num: Float): Float {
    var value = num*num
    return value.formatToSinglePrecision().toFloat()
}

private fun floatSquareRoot(num: Float): Float {
    var value = 0f

    if(num<0) {
        var absNum = abs(num)
        var eAbsSquareRoot = Expression("√$absNum")
        value = eAbsSquareRoot.calculate().toFloat()//.formatToSinglePrecision().toFloat()
    } else if(num>=0) {
        var eSquareRoot = Expression("√$num")
        value = eSquareRoot.calculate().toFloat()//.formatToSinglePrecision().toFloat()
    }

    return value.toFloat()
}