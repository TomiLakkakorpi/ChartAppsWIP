package com.example.barchartapp

import android.graphics.Typeface
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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

import co.yml.charts.common.components.Legends
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

import com.example.barchartapp.ui.theme.color1
import com.example.barchartapp.ui.theme.color2
import com.example.barchartapp.ui.theme.color3
import com.example.barchartapp.ui.theme.color4
import com.example.barchartapp.ui.theme.color5
import com.example.barchartapp.ui.theme.color7
import com.example.barchartapp.ui.theme.color8
import com.example.barchartapp.ui.theme.color9
import com.example.barchartapp.ui.theme.color10
import com.example.barchartapp.ui.theme.color15
import com.example.barchartapp.ui.theme.color16

@Composable
fun DonutChartScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Suomen väkiluku kaupungittain",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(0.dp, 20.dp),
                fontSize = 20.sp
            )

            DrawDonutChart()

            Box(
                modifier = Modifier.padding(10.dp),
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

@Composable
fun DrawDonutChart() {
    val dataList = PieChartData(
        slices = listOf(
            PieChartData.Slice("Helsinki", 684018f, color = color1),
            PieChartData.Slice("Espoo", 320931f, color = color2),
            PieChartData.Slice("Tampere", 260180f, color = color3),
            PieChartData.Slice("Vantaa", 251269f, color = color4),
            PieChartData.Slice("Oulu", 216152f, color = color5),
            PieChartData.Slice("Turku", 206073f, color = color7),
            PieChartData.Slice("Jyväskylä", 149194f, color = color8),
            PieChartData.Slice("Kuopio", 125666f, color = color9),
            PieChartData.Slice("Lahti", 121337f, color = color10),
            PieChartData.Slice("Pori", 83305f, color = color15),
            PieChartData.Slice("Muu Suomi", 3129089f, color = color16)
        ),
        plotType = PlotType.Donut
    )

    val donutChartConfig = PieChartConfig(
        labelVisible = true,
        strokeWidth = 120f,
        labelColor = Color.Black,
        activeSliceAlpha = .9f,
        isEllipsizeEnabled = true,
        labelTypeface = Typeface.defaultFromStyle(Typeface.BOLD),
        isAnimationEnable = true,
        chartPadding = 25,
        labelFontSize = 42.sp,
    )

    Column(modifier = Modifier.height(500.dp)) {
        Spacer(modifier = Modifier.height(20.dp))
        Legends(legendsConfig = DataUtils.getLegendsConfigFromPieChartData(pieChartData = dataList, 3))
        DonutPieChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            dataList,
            donutChartConfig
        ) {}
    }
}