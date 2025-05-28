package com.example.barchartapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarStyle
import com.example.barchartapp.ui.theme.color1
import com.example.barchartapp.ui.theme.color2
import com.example.barchartapp.ui.theme.color3
import com.example.barchartapp.ui.theme.color4
import com.example.barchartapp.ui.theme.color5
import com.example.barchartapp.ui.theme.color6

@Composable
fun BarChartScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(10.dp, 20.dp, 10.dp, 0.dp),
                textAlign = TextAlign.Center,
                text = "OAMK hakijamäärät kevät 2023 - syksy 2025",
                fontSize = 15.sp
            )

            DrawBarChart(navController)
        }
    }
}

@Composable
fun DrawBarChart(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val dataList = arrayListOf(
            BarData(point = Point(1F, 3135F), color = color1, label = "K 2023"),
            BarData(point = Point(2F, 11388F), color = color2, label = "S 2023"),
            BarData(point = Point(3F, 5307F), color = color3, label = "K 2024"),
            BarData(point = Point(4F, 12528F), color = color4, label = "S 2024"),
            BarData(point = Point(5F, 3198F), color = color5, label = "K 2025"),
            BarData(point = Point(6F, 10956F), color = color6, label = "S 2025"),
        )

        val maxRange = 13000
        val yStepSize = 13

        val xAxisData = AxisData.Builder()
            .axisStepSize(30.dp)
            .steps(dataList.size - 1)
            .bottomPadding(60.dp)
            .axisLabelAngle(45f)
            .labelAndAxisLinePadding(10.dp)
            .startDrawPadding(20.dp)
            .labelData { index -> dataList[index].label }
            .build()

        val yAxisData = AxisData.Builder()
            .steps(yStepSize)
            .labelAndAxisLinePadding(20.dp)
            .axisOffset(10.dp)
            .labelData { index -> (index * (maxRange / yStepSize)).toString() }
            .build()

        val barChartData = BarChartData(
            chartData = dataList,
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

        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BarChart(
                modifier = Modifier
                    .height(350.dp),
                barChartData = barChartData
            )

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