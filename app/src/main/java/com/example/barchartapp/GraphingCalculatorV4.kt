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
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import co.yml.charts.ui.wavechart.WaveChart
import co.yml.charts.ui.wavechart.model.Wave
import co.yml.charts.ui.wavechart.model.WaveChartData
import co.yml.charts.ui.wavechart.model.WavePlotData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.mariuszgromada.math.mxparser.Argument
import org.mariuszgromada.math.mxparser.Expression

var Calculator4lineChartList = mutableListOf<Point>()

@Composable
fun GraphingCalculatorScreen4(navController: NavController) {

    var hText by remember { mutableStateOf("") }
    var kText by remember { mutableStateOf("") }
    var rText by remember { mutableStateOf("") }

    var h by remember { mutableFloatStateOf(0.0f) }
    var k by remember { mutableFloatStateOf(0.0f) }
    var r by remember { mutableFloatStateOf(0.0f) }
    var t by remember { mutableFloatStateOf(0.0f) }

    var t1Value by remember { mutableFloatStateOf(0.0f) }
    var t2Value by remember { mutableFloatStateOf(0.0f) }

    var e1: Expression
    var e2: Expression

    var t2: Argument

    var xValue by remember {mutableFloatStateOf(0.0f)}
    var yValue by remember {mutableFloatStateOf(0.0f)}

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

            if (Calculator4lineChartList.size >= 10) {
                steps = 10
            } else {
                steps = Calculator4lineChartList.size
            }

            val xAxisData = AxisData.Builder()
                .axisStepSize(30.dp)
                .startDrawPadding(48.dp)
                .steps(Calculator4lineChartList.size - 1)
                .labelAndAxisLinePadding(25.dp)
                .labelData { i ->
                    val xMin = Calculator4lineChartList.minOf { it.x }
                    val xMax = Calculator4lineChartList.maxOf { it.x }
                    val xScale = (xMax - xMin) / steps
                    ((i * xScale) + xMin).formatToSinglePrecision()
                }.build()

            val yAxisData = AxisData.Builder()
                .steps(steps)
                .labelAndAxisLinePadding(20.dp)
                .labelData { i ->
                    val yMin = Calculator4lineChartList.minOf { it.y }
                    val yMax = Calculator4lineChartList.maxOf { it.y }
                    val yScale = (yMax - yMin) / steps
                    ((i * yScale) + yMin).formatToSinglePrecision()
                }.build()

            val data = WaveChartData(
                wavePlotData = WavePlotData(
                    lines = listOf(
                        Wave(
                            dataPoints = Calculator4lineChartList,
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
                modifier = Modifier.padding(10.dp),
                text = "Piirretty Kaava: $formula"
            )

            Box(
                modifier = Modifier
                    .width(370.dp)
                    .height(300.dp)
            ) {
                Log.d("FormulaTest", "Checking if chart can be drawed")

                if(Calculator4lineChartList.isNotEmpty()){
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
                modifier = Modifier
                    .padding(0.dp, 50.dp, 0.dp, 0.dp)
                    .width(200.dp),
                value = hText,
                onValueChange = { newText ->
                    hText = newText
                },
                label = {
                    Text(text = "Syötä h")
                },
            )

            TextField(
                modifier = Modifier.width(200.dp),
                value = kText,
                onValueChange = { newText ->
                    kText = newText
                },
                label = {
                    Text(text = "Syötä k")
                },
            )

            TextField(
                modifier = Modifier.width(200.dp),
                value = rText,
                onValueChange = { newText ->
                    rText = newText
                },
                label = {
                    Text(text = "Syötä r")
                },
            )

            Row() {
                Button(
                    modifier = Modifier.padding(0.dp, 0.dp, 20.dp, 0.dp),
                    onClick = {
                        k = kText.toFloat()
                        h = hText.toFloat()
                        r = rText.toFloat()

                        CoroutineScope(IO).launch {
                            while(t <= 2){
                                var xFormula = Argument("x=$h+$r*cos($t π)")
                                e1 = Expression("x", xFormula)

                                xValue = e1.calculate().toFloat()

                                var yFormula = Argument("y=$k+$r*sin($t π)")
                                e2 = Expression("y", yFormula)

                                yValue = e2.calculate().toFloat()

                                Log.d("CircleTest", "h = $h")
                                Log.d("CircleTest", "k = $k")
                                Log.d("CircleTest", "r = $r")
                                Log.d("CircleTest", "t = $t")
                                Log.d("CircleTest", "x value: $xValue")
                                Log.d("CircleTest", "y value: $yValue")
                                Log.d("CircleTest", "-----")

                                Calculator4lineChartList.add(Point(xValue, yValue))

                                t = t + 0.025f
                            }

                            kText = " "
                            kText = ""
                        }
                    }
                ) {
                    Text("Piirrä kaavio")
                }

                Button(
                    onClick = {

                    }
                ) {
                    Text("Tyhjennä taulukko")
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