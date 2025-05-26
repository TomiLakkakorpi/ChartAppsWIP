package com.example.barchartapp

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CustomUIComponent1(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column () {
                var value by remember {mutableStateOf(0)}

                /* Text(
                    textAlign = TextAlign.Center,
                    text = "Datan käyttö",
                    fontSize = 25.sp
                ) */

                CustomUIComponent1(
                    indicatorValue = value,
                    maxIndicatorValue = 100
                )

                TextField(
                    value = value.toString(),
                    onValueChange = {
                        value = if (it.isNotEmpty()) {
                            it.toInt()
                        } else {
                            0
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )

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

@Composable
fun CustomUIComponent1(
    canvasSize: Dp = 300.dp,
    indicatorValue: Int = 0,
    maxIndicatorValue: Int = 100,
    minIndicatorValue: Int = 0,
    backgroundIndicatorColor: Color = Color.LightGray,
    backgroundIndicatorStrokeWidth: Float = 100f,
    foregroundIndicatorColor: Color = Color.DarkGray,
    foregroundIndicatorStrokeWidth: Float = 100f,
    //indicatorStrokeCap: StrokeCap = StrokeCap.Round,
    bigTextFontSize: TextUnit = 40.sp,
    bigTextColor: Color = Color.Black,
    bigTextSuffix: String = "GB",
    smallText: String = "Dataa käytetty",
    smallTextFontSize: TextUnit = 25.sp,
    smallTextColor: Color = Color.Black,
) {
    var allowedIndicatorValue by remember {
        mutableStateOf(maxIndicatorValue)
    }

    //Tarkistetaan onko syötetty arvo sallituissa rajoissa
    //Jos syötetty arvo on yli sallitun, asetetaan arvoksi annettu maksimiarvo
    //Jos syötetty arvo on alle sallitun, asetetaan arvoksi minimiarvo
    allowedIndicatorValue = (if (indicatorValue >= maxIndicatorValue) {
        maxIndicatorValue
    } else if (indicatorValue <= minIndicatorValue) {
        minIndicatorValue
    } else {
        indicatorValue
    })

    var animatedIndicatorValue by remember { mutableStateOf(0f) }
    LaunchedEffect(key1 = allowedIndicatorValue) {
        animatedIndicatorValue = allowedIndicatorValue.toFloat()
    }

    val percentage =
        (animatedIndicatorValue / maxIndicatorValue) * 100

    val sweepAngle by animateFloatAsState(
        targetValue = (2.4 * percentage).toFloat(),
        animationSpec = tween(1000)
    )

    val receivedValue by animateIntAsState(
        targetValue = allowedIndicatorValue,
        animationSpec = tween(1000)
    )

    val animatedBigTextColor by animateColorAsState(
        targetValue = if (allowedIndicatorValue == 0)
            Color.Black
        else
            bigTextColor,
        animationSpec = tween(1000)
    )

    Column(
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {
                val componentSize = size / 1.25f
                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth,
//                    indicatorStokeCap = indicatorStrokeCap
                )
                foregroundIndicator(
                    sweepAngle = sweepAngle,
                    componentSize = componentSize,
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth,
//                    indicatorStokeCap = indicatorStrokeCap
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmbeddedElements(
            bigText = receivedValue,
            bigTextFontSize = bigTextFontSize,
            bigTextColor = animatedBigTextColor,
            bigTextSuffix = bigTextSuffix,
            smallText = smallText,
            smallTextColor = smallTextColor,
            smallTextFontSize = smallTextFontSize
        )
    }
}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
//    indicatorStokeCap: StrokeCap
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = 240f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

fun DrawScope.foregroundIndicator(
    sweepAngle: Float,
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
//    indicatorStokeCap: StrokeCap
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

@Composable
fun EmbeddedElements(
    bigText: Int,
    bigTextFontSize: TextUnit,
    bigTextColor: Color,
    bigTextSuffix: String,
    smallText: String,
    smallTextColor: Color,
    smallTextFontSize: TextUnit
) {
    Text(
        text = smallText,
        color = smallTextColor,
        fontSize = smallTextFontSize,
        textAlign = TextAlign.Center
    )
    Text(
        text = "$bigText ${bigTextSuffix.take(2)}",
        color = bigTextColor,
        fontSize = bigTextFontSize,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}