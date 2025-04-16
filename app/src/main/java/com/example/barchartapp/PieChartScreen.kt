package com.example.barchartapp

import android.text.TextUtils
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.components.Legends
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarStyle
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

@Composable
fun PieChartScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val pieChartData = PieChartData(
            slices = listOf(
                PieChartData.Slice("Helsinki 681802", 681802f, Color.Green),
                PieChartData.Slice("Espoo 318507", 318507f, Color.Blue),
                PieChartData.Slice("Tampere 258770", 258770f, Color.Red),
                PieChartData.Slice("Vantaa 250037", 250073f, Color.Yellow),
                PieChartData.Slice("Oulu 215503", 215503f, Color.Gray),
                PieChartData.Slice("Turku 204618", 204618f, Color.LightGray),
                PieChartData.Slice("Jyväskylä 148622", 148622f, Color.Cyan),
                PieChartData.Slice("Kuopio 124825", 124825f, Color.Magenta),
                PieChartData.Slice("Lahti 121202", 121202f, Color.White),
                PieChartData.Slice("Pori 83334", 83334f, Color.Black),
            ),
            plotType = PlotType.Pie
        )


        val pieChartConfig =
            PieChartConfig(
                labelVisible = true,
                activeSliceAlpha = .9f,
                isEllipsizeEnabled = true,
                sliceLabelEllipsizeAt = TextUtils.TruncateAt.MIDDLE,
                isAnimationEnable = true,
                chartPadding = 30,
                backgroundColor = Color.White,
                showSliceLabels = false,
                animationDuration = 1500
            )
        Column(modifier = Modifier.height(500.dp)) {
            Spacer(modifier = Modifier.height(20.dp))
            Legends(legendsConfig = DataUtils.getLegendsConfigFromPieChartData(pieChartData, 3))
            PieChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                pieChartData,
                pieChartConfig
            ) {

            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PieChartScreenPreview() {
    PieChartScreen(navController = rememberNavController())
}


/* Pie Chart with random values

@Composable
fun PieChartRandomValues() {
    val pieChartData = DataUtils.getPieChartData()
    val pieChartConfig =
        PieChartConfig(
            labelVisible = true,
            activeSliceAlpha = .9f,
            isEllipsizeEnabled = true,
            sliceLabelEllipsizeAt = TextUtils.TruncateAt.MIDDLE,
            isAnimationEnable = true,
            chartPadding = 30,
            backgroundColor = Color.White,
            showSliceLabels = false,
            animationDuration = 1500
        )
    Column(modifier = Modifier.height(500.dp)) {
        Spacer(modifier = Modifier.height(20.dp))
        Legends(legendsConfig = DataUtils.getLegendsConfigFromPieChartData(pieChartData, 3))
        PieChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            pieChartData,
            pieChartConfig
        ) {

        }
    }
}
*/