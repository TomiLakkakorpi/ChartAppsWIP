package com.example.barchartapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun WaveChartScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .height(800.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            Box() {


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
}

@Composable
@Preview(showBackground = true)
fun WaveChartScreenPreview() {
    WaveChartScreen(navController = rememberNavController())
}