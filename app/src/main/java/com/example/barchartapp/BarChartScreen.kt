package com.example.barchartapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarStyle

@Composable
fun BarChartScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .height(800.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            Box() {
                val maxRange = 700000F
                val yStepSize = 7

                val list = arrayListOf(
                    BarData(
                        point = Point(1F, 681802F),
                        Color.Cyan,
                        label = "Helsinki",
                    ),

                    BarData(
                        point = Point(2F, 318507F),
                        Color.Cyan,
                        label = "Espoo",
                    ),

                    BarData(
                        point = Point(3F, 258770F),
                        Color.Cyan,
                        label = "Tampere",
                    ),

                    BarData(
                        point = Point(4F, 250073F),
                        Color.Cyan,
                        label = "Vantaa",
                    ),

                    BarData(
                        point = Point(5F, 215530F),
                        Color.Cyan,
                        label = "Oulu",
                    ),

                    BarData(
                        point = Point(6F, 204618F),
                        Color.Cyan,
                        label = "Turku",
                    ),

                    /* BarData(
                        point = Point(7F, 148622F),
                        Color.Cyan,
                        label = "Jyväskylä",
                        ),

                    BarData(
                        point = Point(8F, 124825F),
                        Color.Cyan,
                        label = "Kuopio",
                        ),

                    BarData(
                        point = Point(9F, 121202F),
                        Color.Cyan,
                        label = "Lahti",
                        ),

                    BarData(
                        point = Point(10F, 83334F),
                        Color.Cyan,
                        label = "Pori",
                        ), */
                )

                val xAxisData = AxisData.Builder()
                    .axisStepSize(30.dp)
                    .steps(list.size - 1)
                    .bottomPadding(40.dp)
                    .axisLabelAngle(20f)
                    .startDrawPadding(25.dp)
                    //.startPadding(10.dp)
                    .labelData { index -> list[index].label }
                    .build()

                val yAxisData = AxisData.Builder()
                    .steps(yStepSize)
                    .labelAndAxisLinePadding(20.dp)
                    .axisOffset(10.dp)
                    .labelData { index -> (index * (maxRange / yStepSize)).toString() }
                    .build()

                val barChartData = BarChartData(
                    chartData = list,
                    xAxisData = xAxisData,
                    yAxisData = yAxisData,
                    barStyle = BarStyle(
                        paddingBetweenBars = 25.dp,
                        barWidth = 20.dp
                    ),
                    showYAxis = true,
                    showXAxis = true,
                    horizontalExtraSpace = 50.dp
                )
                BarChart(modifier = Modifier.height(350.dp), barChartData = barChartData)
            }

            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .clickable {
                        navController.navigateUp()
                    },
                textAlign = TextAlign.Center,
                text = "Takaisin päävalikkoon",
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BarChartScreenPreview() {
    BarChartScreen(navController = rememberNavController())
}

/* Bar Chart with random values
@Composable
fun BarChartRandomValues() {
    val maxRange = 100
    val barData = DataUtils.getGradientBarChartData(50, 100)
    val yStepSize = 10

    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(barData.size - 1)
        .bottomPadding(40.dp)
        .topPadding(20.dp)
        .axisLabelAngle(20f)
        .startDrawPadding(48.dp)
        .labelData { index -> barData[index].label }
        .build()

    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()

    val barChartData = BarChartData(
        chartData = barData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        barStyle = BarStyle(paddingBetweenBars = 20.dp,
            barWidth = 35.dp,
            //isGradientEnabled = true,
            selectionHighlightData = SelectionHighlightData(
                highlightBarColor = Color.Black,
                highlightTextBackgroundColor = Color.Green,
                popUpLabel = { _, y -> " Value : $y " }
            )),
        showYAxis = true,
        showXAxis = true,
        horizontalExtraSpace = 20.dp
    )
    BarChart(modifier = Modifier.height(400.dp), barChartData = barChartData)
}
 */