package com.example.barchartapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.barchartapp.ui.theme.color1

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row() {
            Column() {
                // Header
                Text(
                    text = "Kaaviot ja kuvaajat",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(5.dp)
                )

                Button(
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    onClick = {

                        navController.navigate(route = Screen.BarChart.route)
                    }
                ) {
                    Text(
                        fontSize = 13.sp,
                        text = "BarChartAppV1"
                    )
                }

                Button(
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        navController.navigate(route = Screen.LineChart.route)
                    }
                ) {
                    Text(
                        fontSize = 13.sp,
                        text = "LineChartAppV1"
                    )
                }

                Button(
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        navController.navigate(route = Screen.WaveChart.route)
                    }
                ) {
                    Text(
                        fontSize = 13.sp,
                        text = "WaveChartAppV1"
                    )
                }

                Button(
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        navController.navigate(route = Screen.PieChart.route)
                    }
                ) {
                    Text(
                        fontSize = 13.sp,
                        text = "PieChartAppV1"
                    )
                }

                Button(
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        navController.navigate(route = Screen.PieChart2.route)
                    }
                ) {
                    Text(
                        fontSize = 13.sp,
                        text = "PieChartAppV2"
                    )
                }

                Button(
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        navController.navigate(route = Screen.PieChart3.route)
                    }
                ) {
                    Text(
                        fontSize = 13.sp,
                        text = "PieChartAppV3"
                    )
                }

                Button(
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        navController.navigate(route = Screen.DonutChart.route)
                    }
                ) {
                    Text(
                        fontSize = 13.sp,
                        text = "DonutChartAppV1"
                    )
                }

                Button(
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        navController.navigate(route = Screen.BubbleChart.route)
                    }
                ) {
                    Text(
                        fontSize = 13.sp,
                        text = "BubbleChartAppV1"
                    )
                }

                Button(
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        navController.navigate(route = Screen.CombinedChart.route)
                    }
                ) {
                    Text(
                        fontSize = 13.sp,
                        text = "CombinedChartAppV1"
                    )
                }

                Button(
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        navController.navigate(route = Screen.CustomUIComponent1.route)
                    }
                ) {
                    Text(
                        fontSize = 13.sp,
                        text = "CustomUIComponent1"
                    )
                }
            }

            Column() {
                // Header
                Text(
                    text = "Graafiset laskimet",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(5.dp)
                )

                Button(
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        navController.navigate(route = Screen.UserInputExample1.route)
                    }
                ) {
                    Text(
                        fontSize = 13.sp,
                        text = "User value input"
                    )
                }

                Button(
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        navController.navigate(route = Screen.GraphingCalculatorScreen1.route)
                    }
                ) {
                    Text(
                        fontSize = 13.sp,
                        text = "Graphing Calculator V1 \nYksi kaava"
                    )
                }

                Button(
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        navController.navigate(route = Screen.GraphingCalculatorScreen2.route)
                    }
                ) {
                    Text(
                        fontSize = 13.sp,
                        text = "Graphing Calculator V2 \nYksi kaava, muokattava piirtoalue"
                    )
                }

                Button(
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        navController.navigate(route = Screen.GraphingCalculatorScreen3.route)
                    }
                ) {
                    Text(
                        fontSize = 13.sp,
                        text = "Graphing Calculator V3 \nKaksi kaavaa"
                    )
                }

                Button(
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        navController.navigate(route = Screen.GraphingCalculatorScreen4.route)
                    }
                ) {
                    Text(
                        fontSize = 13.sp,
                        text = "Graphing Calculator V4 \nYmpyrän piirto"
                    )
                }

                Button(
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        navController.navigate(route = Screen.GraphingCalculatorScreen5.route)
                    }
                ) {
                    Text(
                        fontSize = 13.sp,
                        text = "Graphing Calculator V5 \nDatan muunnokset"
                    )
                }

                Button(
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        navController.navigate(route = Screen.GraphingCalculatorScreen6.route)
                    }
                ) {
                    Text(
                        fontSize = 12.sp,
                        text = "Graphing Calculator V6 \nOminaisuudet Yhdistetty"
                    )
                }
            }
        }
    }
}