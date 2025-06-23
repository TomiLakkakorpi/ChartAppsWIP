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
import androidx.compose.ui.text.TextStyle
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

    var hDisplayText = ""
    var kDisplayText = ""
    var rDisplayText = ""

    var h by remember { mutableFloatStateOf(0.0f) }
    var k by remember { mutableFloatStateOf(0.0f) }
    var r by remember { mutableFloatStateOf(0.0f) }
    var t by remember { mutableFloatStateOf(0.0f) }

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
                modifier = Modifier.padding(10.dp),
                text = "Piirretty kaava:"
            )

            Box(
                modifier = Modifier
                    .width(370.dp)
                    .height(300.dp)
            ) {
                Log.d("FormulaTest", "Checking if chart can be drawed")

                if(Calculator4lineChartList.isNotEmpty()){
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
                Text(
                    text = "(x$hDisplayText)² - (y$kDisplayText)² = $rDisplayText²",
                    fontSize = 25.sp
                )
            }

            Row() {
                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    text = "Syötä h: ",
                    fontSize = 20.sp
                )

                TextField(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(50.dp)
                        .width(100.dp),
                    value = hText,
                    onValueChange = { newText ->
                        hText = newText
                    },
                )
            }

            Row() {
                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    text = "Syötä k: ",
                    fontSize = 20.sp
                )

                TextField(
                    textStyle = TextStyle(fontSize = 15.sp),
                    modifier = Modifier
                        .padding(10.dp)
                        .height(50.dp)
                        .width(100.dp),
                    value = kText,
                    onValueChange = { newText ->
                        kText = newText
                    }
                )
            }

            Row() {
                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    text = "Syötä r: ",
                    fontSize = 20.sp
                )

                TextField(
                    textStyle = TextStyle(fontSize = 15.sp),
                    modifier = Modifier
                        .padding(10.dp)
                        .height(50.dp)
                        .width(100.dp),
                    value = rText,
                    onValueChange = { newText ->
                        rText = newText
                    }
                )
            }

            Row() {
                Button(
                    modifier = Modifier.padding(0.dp, 0.dp, 20.dp, 0.dp),
                    onClick = {

                        //h ja k pitää ottaa vastakkaiset arvot!!
                        k = kText.toFloat()
                        h = hText.toFloat()
                        r = rText.toFloat()

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

                                t = t + 0.025f
                            }

                            uiUpdate = " "
                            uiUpdate = ""
                        }
                    }
                ) {
                    Text("Piirrä ympyrä")
                }

                Button(
                    onClick = {

                    }
                ) {
                    Text("Tyhjennä kuvaaja")
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

            Text(text = uiUpdate)
        }
    }
}

private fun floatAddition(numA: Float, numB: Float): Float {
    var value = numA + numB
    return value.formatToSinglePrecision().toFloat()
}