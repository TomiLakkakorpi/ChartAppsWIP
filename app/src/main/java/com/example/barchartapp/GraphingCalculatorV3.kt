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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.extensions.formatToSinglePrecision
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.mariuszgromada.math.mxparser.Argument
import org.mariuszgromada.math.mxparser.Expression

var Calculator3lineChartList1 = mutableListOf<Point>()
var Calculator3lineChartList2 = mutableListOf<Point>()

@Composable
fun GraphingCalculatorScreen3(navController: NavController) {

    //License.iConfirmNonCommercialUse("")

    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }

    var xStart by remember { mutableFloatStateOf(-5.0f) }
    var xEnd by remember { mutableFloatStateOf(5.0f) }
    var xIncrement by remember { mutableFloatStateOf(0.1f) }
    var xValue1 by remember {mutableFloatStateOf(-5.0f)}
    var xValue2 by remember {mutableFloatStateOf(-5.0f)}

    var e2: Expression
    var x2: Argument
    var y2: Argument

    var e1: Expression
    var x1: Argument
    var y1: Argument

    var formula1 by remember {mutableStateOf("")}
    var formula2 by remember {mutableStateOf("")}

    var chart1Ready by remember {mutableStateOf(false)}
    var chart2Ready by remember {mutableStateOf(false)}

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column()
        {
            val context = LocalContext.current
            var steps: Int

            if (Calculator3lineChartList1.size >= 10) {
                steps = 10
            } else {
                steps = Calculator3lineChartList1.size
            }

            //var yMin = 0f
            //var yMax = 0f

            val xAxisData = AxisData.Builder()
                .axisStepSize(30.dp)
                //.startDrawPadding(48.dp)
                .steps(Calculator3lineChartList1.size - 1)
                .labelAndAxisLinePadding(25.dp)
                .labelData { i ->
                    val xMin = Calculator3lineChartList1.minOf { it.x }
                    val xMax = Calculator3lineChartList1.maxOf { it.x }
                    val xScale = (xMax - xMin) / steps
                    ((i * xScale) + xMin).formatToSinglePrecision()
                }.build()

            val yAxisData = AxisData.Builder()
                .steps(steps)
                .labelAndAxisLinePadding(20.dp)
                .labelData { i ->
                    /*if(Calculator3lineChartList1.minOf { it.y } <= Calculator3lineChartList2.minOf { it.y }) {
                        yMin = Calculator3lineChartList1.minOf { it.y }
                    } else {
                        yMin = Calculator3lineChartList2.minOf { it.y }
                    }

                    if(Calculator3lineChartList1.maxOf { it.y } >= Calculator3lineChartList2.maxOf { it.y }) {
                        yMax = Calculator3lineChartList1.maxOf { it.y }
                    } else {
                        yMax = Calculator3lineChartList2.maxOf { it.y }
                    } */
                    val yMin = Calculator3lineChartList1.minOf { it.y }
                    val yMax = Calculator3lineChartList1.maxOf { it.y }
                    val yScale = (yMax - yMin) / steps
                    ((i * yScale) + yMin).formatToSinglePrecision()
                }.build()

            val data = LineChartData(
                linePlotData = LinePlotData(
                    lines = listOf(
                        Line(
                            dataPoints = Calculator3lineChartList1,
                            selectionHighlightPoint = SelectionHighlightPoint(),
                            shadowUnderLine = ShadowUnderLine(),
                            selectionHighlightPopUp = SelectionHighlightPopUp()
                        ),
                        Line(
                            dataPoints = Calculator3lineChartList2,
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
                text = "Piirretyt kaavat"
            )

            Text(
                modifier = Modifier.padding(10.dp),
                text = "Kaava 1: $formula1"
            )

            Text(
                    modifier = Modifier.padding(10.dp),
            text = "Kaava 2: $formula2"
            )

            Box(
                modifier = Modifier
                    .width(370.dp)
                    .height(300.dp)
            ) {
                Log.d("FormulaTest", "Checking if chart can be drawed")

                if(Calculator3lineChartList1.isNotEmpty() && Calculator3lineChartList2.isNotEmpty()) {
                    if(chart1Ready && chart2Ready) {
                        LineChart(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(350.dp),
                            lineChartData = data
                        )
                    }
                }
            }

            Row() {

                    TextField(
                        modifier = Modifier
                            .width(170.dp)
                            .padding(5.dp),
                        value = text1,
                        onValueChange = { newText ->
                            text1 = newText
                        },
                        label = {
                            Text(text = "Kirjoita kaava 1")
                        },
                    )

                    TextField(
                        modifier = Modifier
                            .width(170.dp)
                            .padding(5.dp),
                        value = text2,
                        onValueChange = { newText ->
                            text2 = newText
                        },
                        label = {
                            Text(text = "Kirjoita kaava 2")
                        },
                    )

            }

            Row() {
                Button(
                    modifier = Modifier.padding(0.dp, 0.dp, 20.dp, 0.dp),
                    onClick = {
                        if(text1.isNotEmpty() && text2.isNotEmpty()) {
                            Toast.makeText(context, "Kaavoja lasketaan, odota hetki!", Toast.LENGTH_SHORT).show()

                            CoroutineScope(IO).launch {
                                while(xValue1 <= xEnd) {
                                    x1 = Argument("x=$xValue1")
                                    y1 = Argument(text1, x1)
                                    e1 = Expression("y", y1)

                                    Calculator3lineChartList1.add(Point(xValue1, e1.calculate().toFloat(), ""))

                                    Log.d("FormulaTest", "Point added to list 1 x: $xValue1, y: " + e1.calculate().toString())

                                    xValue1 = floatAddition(xValue1, xIncrement)
                                }

                                text1 = ""
                            }
                            Log.d("FormulaTest", "Chart 1 ready")
                            chart1Ready = true

                            CoroutineScope(IO).launch{
                                while(xValue2 <= xEnd) {
                                    x2 = Argument("x=$xValue2")
                                    y2 = Argument(text2, x2)
                                    e2 = Expression("y", y2)

                                    Calculator3lineChartList2.add(Point(xValue2, e2.calculate().toFloat(), ""))

                                    Log.d("FormulaTest", "Point added to list 2 x: $xValue2, y: " + e2.calculate().toString())

                                    xValue2 = floatAddition(xValue2, xIncrement)
                                }
                                text2 = ""
                            }
                            chart2Ready = true
                            Log.d("FormulaTest", "Chart 2 ready")

                        } else if(!text1.isNotEmpty() && text2.isNotEmpty()){
                            Toast.makeText(context, "Syötä kaava 1!", Toast.LENGTH_SHORT).show()
                        } else if(text1.isNotEmpty() && !text2.isNotEmpty()) {
                            Toast.makeText(context, "Syötä kaava 2!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Syötä kaavat", Toast.LENGTH_SHORT).show()
                        }

                        xValue1 = xStart
                        formula1 = text1
                        formula2 = text2
                    }
                ) {
                    Text("Piirrä kaavat")
                }

                Button(
                    onClick = {
                        if(Calculator3lineChartList1.isEmpty() && Calculator3lineChartList2.isEmpty()) {
                            Toast.makeText(context, "Kaavat ovat jo tyhjiä!", Toast.LENGTH_SHORT).show()
                        }

                        while(Calculator3lineChartList1.isNotEmpty()) {
                            Calculator3lineChartList1.removeAt(Calculator3lineChartList1.size -1)
                        }
                        chart1Ready = false
                        Log.d("FormulaTest", "Chart 1 cleared")
                        formula1 = ""
                        xValue1 = xStart

                        while(Calculator3lineChartList2.isNotEmpty()) {
                            Calculator3lineChartList2.removeAt(Calculator3lineChartList2.size -1)
                        }
                        chart2Ready = false
                        Log.d("FormulaTest", "Chart 2 cleared")
                        formula2 = ""
                        xValue2 = xStart
                    }
                ) {
                    Text("Tyhjennä kaavat")
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
                            Text(text = "x Lähtöarvo")
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
                            Text(text = "x Loppuarvo")
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
                            Text(text = "x lisäysarvo")
                        },
                    )

                    Button(
                        shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                        modifier = Modifier.padding(5.dp),
                        onClick = {
                            xValue1 = xStart
                            xValue2 = xStart
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

private fun floatAddition(numA: Float, numB: Float): Float {
    var value = numA + numB
    return value.formatToSinglePrecision().toFloat()
}