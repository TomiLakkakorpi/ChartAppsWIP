package com.example.barchartapp

import android.text.TextUtils
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import co.yml.charts.common.components.Legends
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.barchartapp.ui.theme.color1
import com.example.barchartapp.ui.theme.color10
import com.example.barchartapp.ui.theme.color2
import com.example.barchartapp.ui.theme.color3
import com.example.barchartapp.ui.theme.color4
import com.example.barchartapp.ui.theme.color5
import com.example.barchartapp.ui.theme.color6
import com.example.barchartapp.ui.theme.color7
import com.example.barchartapp.ui.theme.color8
import com.example.barchartapp.ui.theme.color9

@Composable
fun PieChartScreen(navController: NavController) {
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

            //Kutsutaan funktiota, joka piirtää piirakkakaavion
            DrawPieChart()

            Box(
                modifier = Modifier.padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
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

//Luodaan funktio, jossa konfiguroidaan piirakkakaavio
@Composable
fun DrawPieChart() {
    //Luodaan dynaaminen lista, johon asetetaan arvoksi lista suomen kaupunkeja, niiden väkiluvut sekä siivulle haluttu väri.
    val dataList = PieChartData(
        slices = arrayListOf(
            //Lisätään kaavioon siivut, lisätään siivuille otsikko, arvo ja väri
            PieChartData.Slice("Helsinki", 684018f, color = color1),
            PieChartData.Slice("Espoo", 320931f, color = color2),
            PieChartData.Slice("Tampere", 260180f, color = color3),
            PieChartData.Slice("Vantaa", 251269f, color = color4),
            PieChartData.Slice("Oulu", 216152f, color = color5),
            PieChartData.Slice("Turku", 206073f, color = color6),
            PieChartData.Slice("Jyväskylä", 149194f, color = color7),
            PieChartData.Slice("Kuopio", 125666f, color = color8),
            PieChartData.Slice("Lahti", 121337f, color = color9),
            PieChartData.Slice("Pori", 83305f, color = color10)
        ),
        //Määritellään minkälaisen kaavion haluamme
        plotType = PlotType.Pie
    )

    //Luodaan konfigurointi arvo, jossa määritellään kaaviolle eri parametreja
    val pieChartConfig = PieChartConfig(
        labelVisible = true,
        activeSliceAlpha = .9f,
        isEllipsizeEnabled = true,
        sliceLabelEllipsizeAt = TextUtils.TruncateAt.MIDDLE,
        isAnimationEnable = true,
        chartPadding = 30,
        backgroundColor = Color.White,
        showSliceLabels = false,
        animationDuration = 2000,
    )

    //Lisätään sarake, jonka korkeudeksi määritetään 500dp
    Column(modifier = Modifier.height(500.dp)) {

        //Lisätään sarakkeiden väliin 20dp rako
        Spacer(modifier = Modifier.height(20.dp))

        //Muodostetaan selitteiden luettelo ruudukkomuotoon
        Legends(legendsConfig = DataUtils.getLegendsConfigFromPieChartData(dataList, 3))

        //Kutsutaan funktiota, joka piirtää piirakkakaavion
        PieChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            dataList,
            pieChartConfig
        ) {}
    }
}