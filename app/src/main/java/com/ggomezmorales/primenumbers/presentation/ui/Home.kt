package com.ggomezmorales.primenumbers.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ggomezmorales.primenumbers.R
import com.ggomezmorales.primenumbers.presentation.PrimeCalculatorModel
import com.ggomezmorales.primenumbers.presentation.composables.InputField
import com.ggomezmorales.primenumbers.ui.theme.Asul

@Preview(showSystemUi = true)
@Composable
fun Home(primeCalculatorModel: PrimeCalculatorModel = PrimeCalculatorModel()) {
    var textShow by remember { mutableStateOf("") }
    var listResult by remember { mutableStateOf(emptyList<Int>()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff22577A)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.main_title),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            fontSize = 35.sp,
            color = Color(0xFFcfd8e5),
            fontFamily = Asul,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            InputField(
                title = "Enter an integer number (n ≥ 2)",
                onButtonClicked = { value ->
                    val isPrime = primeCalculatorModel.isPrime(value.toInt())
                    textShow = "The number $value ${if (isPrime) "is" else "is not"} prime."
                })
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .align(Alignment.CenterHorizontally),
                text = textShow,
                fontSize = 20.sp,
                color = Color(0xFFffffff),
                fontFamily = Asul,
            )
            InputField(
                title = "Enter the amount of prime numbers (n ≥ 0)",
                onButtonClicked = { value ->
                    listResult = primeCalculatorModel.giveMeXPrime(value.toInt())
                }
            )
            LazyColumn(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemsIndexed(listResult.chunked(5)) { _, numbers ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        numbers.forEach { number ->
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(10.dp),
                                text = number.toString(),
                                fontSize = 15.sp,
                                color = Color(0xFFffffff),
                                fontFamily = Asul,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}