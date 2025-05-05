package com.example.barchartapp

import android.graphics.Typeface
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.barchartapp.ui.theme.color6
import com.example.barchartapp.ui.theme.color7
import com.example.barchartapp.ui.theme.color8
import com.example.barchartapp.ui.theme.color9
import com.example.barchartapp.ui.theme.color10
import com.example.barchartapp.ui.theme.color11

@Composable
fun DonutChartScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column () {
            Box() {
                val donutChartData = PieChartData(
                    slices = listOf(
                        PieChartData.Slice("Helsinki", 681802f, color = color1),
                        PieChartData.Slice("Espoo", 318507f, color = color2),
                        PieChartData.Slice("Tampere", 258770f, color = color3),
                        PieChartData.Slice("Vantaa", 250073f, color = color4),
                        PieChartData.Slice("Oulu", 215503f, color = color5),
                        PieChartData.Slice("Turku", 204618f, color = color6),
                        PieChartData.Slice("Jyväskylä", 148622f, color = color7),
                        PieChartData.Slice("Kuopio", 124825f, color = color8),
                        PieChartData.Slice("Lahti", 121202f, color = color9),
                        PieChartData.Slice("Pori", 83334f, color = color10),

                        PieChartData.Slice("Muu Suomi", 3129089f, color = color11)
                    ),
                    plotType = PlotType.Donut
                )

                val pieChartConfig =
                    PieChartConfig(
                        labelVisible = true,
                        strokeWidth = 120f,
                        labelColor = Color.Black,
                        activeSliceAlpha = .9f,
                        isEllipsizeEnabled = true,
                        labelTypeface = Typeface.defaultFromStyle(Typeface.BOLD),
                        isAnimationEnable = true,
                        chartPadding = 25,
                        labelFontSize = 42.sp,
                        labelType = PieChartConfig.LabelType.PERCENTAGE
                    )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                ) {
                    Legends(legendsConfig = DataUtils.getLegendsConfigFromPieChartData(pieChartData = donutChartData, 3))
                    DonutPieChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp),
                        donutChartData,
                        pieChartConfig
                    ) {

                    }
                }
            }

            Text(
                modifier = Modifier.clickable {
                    navController.navigateUp()
                },
                text = "Takaisin päävalikkoon",
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
        }
    }
}