package com.example.scientificcalculator

import CalculatorFunction
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import com.example.scientificcalculator.ui.theme.ScientificCalculatorTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow
import java.util.Stack
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScientificCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.background
                ) {
                    CalculatorScreen(result = "15")
                }
            }
        }
    }
}


@Composable
fun CalculatorButton(
    calculatorFunction: CalculatorFunction,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = calculatorFunction.string ?: calculatorFunction.number.toString(),
            fontSize = 28.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CalculatorRow(
    buttons: List<CalculatorFunction>,
    onClick: (CalculatorFunction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        buttons.forEach{ calculatorFunction ->
            CalculatorButton(
                calculatorFunction = calculatorFunction,
                onClick = { onClick(calculatorFunction) },
                modifier.weight(1f),
            )
        }
    }
}

@Composable
fun CalculatorScreen(
    result: String,
    modifier: Modifier = Modifier,
) {
    var result by remember { mutableStateOf("") }

    val updateResult: (CalculatorFunction) -> Unit = { function ->
        result += function.number?.toString() ?: function.string.orEmpty()
    }

    val row1 = listOf(CalculatorFunction.SIN, CalculatorFunction.COS, CalculatorFunction.TAN)
    val row2 = listOf(CalculatorFunction.POWER, CalculatorFunction.ROOT, CalculatorFunction.LOG)
    val row3 = listOf(CalculatorFunction.CLEAR, CalculatorFunction.BRACKETS1, CalculatorFunction.BRACKETS2, CalculatorFunction.DIVIDE)
    val row4 = listOf(CalculatorFunction.ONE, CalculatorFunction.TWO, CalculatorFunction.THREE, CalculatorFunction.MULTIPLY)
    val row5 = listOf(CalculatorFunction.FOUR, CalculatorFunction.FIVE, CalculatorFunction.SIX, CalculatorFunction.MINUS)
    val row6 = listOf(CalculatorFunction.SEVEN, CalculatorFunction.EIGHT, CalculatorFunction.NINE, CalculatorFunction.PLUS)
    val row7 = listOf(CalculatorFunction.DELETE, CalculatorFunction.ZERO, CalculatorFunction.DOT, CalculatorFunction.EQUALS)

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = result,
                fontSize = 40.sp,
                color = Color.Black,
                lineHeight = 40.sp,
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            )

            CalculatorRow(buttons = row1, onClick = updateResult)
            CalculatorRow(buttons = row2, onClick = updateResult)
            CalculatorRow(buttons = row3, onClick = updateResult)
            CalculatorRow(buttons = row4, onClick = updateResult)
            CalculatorRow(buttons = row5, onClick = updateResult)
            CalculatorRow(buttons = row6, onClick = updateResult)
            CalculatorRow(buttons = row7, onClick = updateResult)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    ScientificCalculatorTheme {
        CalculatorScreen(result = "10")
    }
}


//@Preview
//@Composable
//fun RowPreview() {
//    ScientificCalculatorTheme {
//        CalculatorRow(
//            buttons = listOf(
//                CalculatorFunction.ONE,
//                CalculatorFunction.TWO,
//                CalculatorFunction.THREE,
//                CalculatorFunction.MULTIPLY,
//            )
//        )
//    }
//}



@Preview(showBackground = false)
@Composable
fun ButtonPreview() {
    ScientificCalculatorTheme {
        CalculatorButton(
            CalculatorFunction.SIN,
            onClick = {})
    }
}