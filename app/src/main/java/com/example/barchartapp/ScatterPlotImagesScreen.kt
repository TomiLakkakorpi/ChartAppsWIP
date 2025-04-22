package com.example.barchartapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ScatterPlotImagesScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column () {
            Box() {
                // Code here for Scatter plot screen with images
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