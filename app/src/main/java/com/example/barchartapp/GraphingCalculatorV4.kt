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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.extensions.formatToSinglePrecision
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
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
import co.yml.charts.ui.wavechart.model.WavePlotData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.mariuszgromada.math.mxparser.Argument
import org.mariuszgromada.math.mxparser.Expression

var Calculator4lineChartList = mutableListOf<Point>()
var Calculator4lineChartListCenter = mutableListOf<Point>()

@Composable
fun GraphingCalculatorScreen4(navController: NavController) {

    var hText by remember { mutableStateOf("") }
    var kText by remember { mutableStateOf("") }
    var rText by remember { mutableStateOf("") }

    var centerPoint by remember {mutableStateOf("")}

    var hDisplayText = ""
    var kDisplayText = ""
    var rDisplayText = ""

    var h by remember { mutableFloatStateOf(0.0f) }
    var k by remember { mutableFloatStateOf(0.0f) }
    var r by remember { mutableFloatStateOf(0.0f) }
    var t by remember { mutableFloatStateOf(0.0f) }
    var tIncrement by remember {mutableFloatStateOf(0.0f)}

    var e1: Expression
    var e2: Expression

    var xValue by remember {mutableFloatStateOf(0.0f)}
    var yValue by remember {mutableFloatStateOf(0.0f)}

    var uiUpdate by remember {mutableStateOf("")}

    hDisplayText = if(hText == "") {
        "-h"
    } else if(hText == "-") {
        "-h"
    } else if(hText.toFloat() == 0.0f) {
        "-0"
    } else {
        if(hText.toFloat() > 0.0f) {
            "+$hText"
        } else if (hText.toFloat() < 0.0f){
            hText
        } else {
            "-h"
        }
    }

    kDisplayText = if(kText == "") {
        "-k"
    } else if(kText == "-") {
        "-k"
    } else if(kText.toFloat() == 0.0f) {
        "-0"
    } else {
        if(kText.toFloat() > 0.0f) {
            "+$kText"
        } else if (kText.toFloat() < 0.0f){
            kText
        } else {
            "-k"
        }
    }

    rDisplayText = if(rText == "") {
        "r"
    } else if(rText == "-") {
        "r"
    } else if(rText.toFloat() == 0.0f) {
        "0"
    } else {
        if(rText.toFloat() > 0.0f) {
            rText
        } else if (rText.toFloat() < 0.0f){
            "-$rText"
        } else {
            "-r"
        }
    }

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
                .startDrawPadding(5.dp)
                .steps(Calculator4lineChartList.size - 1)
                .labelAndAxisLinePadding(25.dp)
                .labelData { i ->
                    val xMin = Calculator4lineChartList.minOf { it.x } //* 1.25f
                    val xMax = Calculator4lineChartList.maxOf { it.x } //* 1.25f
                    val xScale = (xMax - xMin) / steps
                    ((i * xScale) + xMin).formatToSinglePrecision()
                }.build()

            val yAxisData = AxisData.Builder()
                .steps(steps)
                .labelAndAxisLinePadding(20.dp)
                .labelData { i ->
                    val yMin = Calculator4lineChartList.minOf { it.y } //* 1.25f
                    val yMax = Calculator4lineChartList.maxOf { it.y } //* 1.25f
                    val yScale = (yMax - yMin) / steps
                    ((i * yScale) + yMin).formatToSinglePrecision()
                }.build()

            val data = LineChartData(
                linePlotData = LinePlotData(
                    lines = listOf(
                        Line(
                            dataPoints = Calculator4lineChartList,
                            lineStyle = LineStyle(color = Color.Black),
                            selectionHighlightPoint = SelectionHighlightPoint(),
                            //shadowUnderLine = ShadowUnderLine(),
                            selectionHighlightPopUp = SelectionHighlightPopUp()
                            //waveFillColor = WaveFillColor(topColor = Color.Green, bottomColor = Color.Red),
                        ),
                        Line(
                            dataPoints = Calculator4lineChartListCenter,
                            lineStyle = LineStyle(color = Color.Black),
                            selectionHighlightPoint = SelectionHighlightPoint(),
                            //shadowUnderLine = ShadowUnderLine(),
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
                modifier = Modifier.padding(20.dp),
                fontSize = 18.sp,
                text = "Piirrä ympyrä kuvaajaan täydentämällä alla olevaa kaavaa "
                        //"\n(x²-h) + (y²-k) = r² " +
                        //"\nx = h + r * cos(t), jossa t=[0-2π]" +
                        //"\ny = k + r * sin(t), jossa t=[0-2π]"
            )

            Box(
                modifier = Modifier
                    .width(370.dp)
                    .height(300.dp)
            ) {
                Log.d("FormulaTest", "Checking if chart can be drawed")

                if(Calculator4lineChartList.isNotEmpty() && Calculator4lineChartListCenter.isNotEmpty()){
                    Log.d("FormulaTest", "Drawing Chart")
                    LineChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp),
                        lineChartData = data
                    )
                }
            }

            Row() {
                Column() {
                    Text(
                        modifier = Modifier.width(150.dp),
                        text = "Kaava:",
                        fontSize = 20.sp
                    )

                    Text(
                        modifier = Modifier.width(150.dp),
                        text = "Keskipiste:",
                        fontSize = 20.sp
                    )
                }
                Column() {
                    Text(
                        text = "(x$hDisplayText)² + (y$kDisplayText)² = $rDisplayText²",
                        fontSize = 20.sp
                    )

                    Text(
                        text = centerPoint,
                        fontSize = 20.sp
                    )
                }
            }

            Row(
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
            ) {
                Column(){
                    Text(
                        modifier = Modifier
                            .padding(10.dp, 13.dp, 10.dp, 0.dp)
                            .height(50.dp)
                            .width(80.dp),
                        text = "Syötä h: ",
                        fontSize = 20.sp
                    )
                    Text(
                        modifier = Modifier
                            .padding(10.dp, 13.dp, 10.dp, 0.dp)
                            .height(50.dp)
                            .width(80.dp),
                        text = "Syötä k: ",
                        fontSize = 20.sp
                    )
                    Text(
                            modifier = Modifier
                                .padding(10.dp, 13.dp, 10.dp, 0.dp)
                                .height(50.dp)
                                .width(80.dp),
                    text = "Syötä r: ",
                    fontSize = 20.sp
                    )
                }

                Column() {
                    TextField(
                        modifier = Modifier
                            .padding(5.dp)
                            .height(50.dp)
                            .width(100.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        value = hText,
                        onValueChange = { newText ->
                            hText = newText
                        },
                    )
                    TextField(
                        textStyle = TextStyle(fontSize = 15.sp),
                        modifier = Modifier
                            .padding(5.dp)
                            .height(50.dp)
                            .width(100.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        value = kText,
                        onValueChange = { newText ->
                            kText = newText
                        }
                    )
                    TextField(
                        textStyle = TextStyle(fontSize = 15.sp),
                        modifier = Modifier
                            .padding(5.dp)
                            .height(50.dp)
                            .width(100.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        value = rText,
                        onValueChange = { newText ->
                            rText = newText
                        }
                    )
                }
            }

            Row() {
                Button(
                    modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp),
                    onClick = {
                        if(Calculator4lineChartList.isEmpty() && Calculator4lineChartListCenter.isEmpty()) {
                            //if(rText.toFloat() < 2) {
                            //    tIncrement = 0.05f
                            //}

                            if(kText.toFloat() > 0) {
                                k = kText.toFloat() - kText.toFloat() - kText.toFloat()
                            } else {
                                k = kText.toFloat() + kText.toFloat() + kText.toFloat()
                            }

                            if(hText.toFloat() > 0) {
                                h = hText.toFloat() - hText.toFloat() - hText.toFloat()
                            } else {
                                h = hText.toFloat() + hText.toFloat() + hText.toFloat()
                            }

                            r = rText.toFloat() * rText.toFloat()

                            //Center dot
                            Calculator4lineChartListCenter.add(Point(h+0.05f, k))
                            Calculator4lineChartListCenter.add(Point(h, k-0.05f))
                            Calculator4lineChartListCenter.add(Point(h-0.05f, k))
                            Calculator4lineChartListCenter.add(Point(h, k-0.05f))
                            Calculator4lineChartListCenter.add(Point(h+0.05f, k))

                            CoroutineScope(IO).launch {
                                while(t <= 2){
                                    var xFormula = Argument("x=$h+$r*cos($t π)")
                                    e1 = Expression("x", xFormula)

                                    xValue = e1.calculate().toFloat()

                                    var yFormula = Argument("y=$k+$r*sin($t π)")
                                    e2 = Expression("y", yFormula)

                                    yValue = e2.calculate().toFloat()

                                    Log.d("CircleTest", "Point added: ($xValue, $yValue)")

                                    Calculator4lineChartList.add(Point(xValue, yValue))

                                    t = t + 0.01f
                                }

                                uiUpdate = " "
                                uiUpdate = ""
                            }
                            centerPoint = "($h,$k)"
                        } else {
                            Toast.makeText(context, "Kaava piirretty jo, tyhjennä taulukko ja yritä uudestaan!", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text("Piirrä ympyrä")
                }

                Button(
                    onClick = {
                        if (Calculator4lineChartList.isNotEmpty() && Calculator4lineChartListCenter.isNotEmpty()) {
                            CoroutineScope(IO).launch {
                                while(Calculator4lineChartList.isNotEmpty()) {
                                    Calculator4lineChartList.removeAt(Calculator4lineChartList.size -1)
                                }
                                while(Calculator4lineChartListCenter.isNotEmpty()) {
                                    Calculator4lineChartListCenter.removeAt(Calculator4lineChartListCenter.size -1)
                                }
                                Log.d("LenghtCheck", "Main Chart ${Calculator4lineChartList.size}")
                                Log.d("LenghtCheck", "Center: ${Calculator4lineChartListCenter.size}")
                            }
                        } else {
                            Toast.makeText(context, "Taulukko on jo tyhjä!", Toast.LENGTH_SHORT).show()
                        }

                        hText = ""
                        kText = ""
                        rText = ""
                        centerPoint = ""
                        t = 0.0f
                    }
                ) {
                    Text("Tyhjennä kuvaaja")
                }
            }

            Box(
                modifier = Modifier.padding(5.dp),
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

            Text(text = uiUpdate)
        }
    }
}

private fun floatAddition(numA: Float, numB: Float): Float {
    var value = numA + numB
    return value.formatToSinglePrecision().toFloat()
}