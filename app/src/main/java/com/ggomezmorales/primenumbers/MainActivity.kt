package com.ggomezmorales.primenumbers


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ggomezmorales.primenumbers.ui.theme.Asul

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val primeCalculatorModel = PrimeCalculatorModel()
        super.onCreate(savedInstanceState)
        setContent {
            Home(primeCalculatorModel)
        }
    }
}

@Composable
fun Home(
    primeCalculatorModel: PrimeCalculatorModel = PrimeCalculatorModel()
) {
    val textShow = remember { mutableStateOf("") }
    val listResult = remember { mutableStateOf(emptyList<Int>()) }
    val context = LocalContext.current
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
                text = "Enter an integer number (n ≥ 2)",
                onButtonClicked = { value ->
                    if (value.toInt() >= 2) {
                        val isPrime = primeCalculatorModel.isPrime(value.toInt())
                        textShow.value =
                            "The number ${value} ${if (isPrime) "is" else "is not"} prime."
                    } else {
                        textShow.value = ""
                        Toast.makeText(context, "Incorrect number", Toast.LENGTH_LONG).show()
                    }
                }
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .align(Alignment.CenterHorizontally),
                text = textShow.value,
                fontSize = 20.sp,
                color = Color(0xFFffffff),
                fontFamily = Asul,
            )
            InputField(
                text = "Enter the amount of prime numbers (n ≥ 0)",
                onButtonClicked = { value ->

                    if (value.toInt() >= 0) {
                        listResult.value = primeCalculatorModel.giveMeXPrime(value.toInt())
                    } else {
                        listResult.value = emptyList()
                        Toast.makeText(context, "Incorrect number", Toast.LENGTH_LONG).show()
                    }
                }
            )
            LazyColumn(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemsIndexed(listResult.value.chunked(5)) { index, numbers ->
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

@Composable
fun InputField(
    text: String = "Title",
    onButtonClicked: (String) -> Unit
) {
    val inputNumber = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xff38A3A5),
                        Color(0xff57CC99)
                    )
                ),
                shape = RoundedCornerShape(10)
            )
    ) {
        Text(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            text = text,
            fontSize = 20.sp,
            color = Color(0xFFf5f3ff),
            fontFamily = Asul
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .background(Color.Transparent),
                value = inputNumber.value,
                onValueChange = {
                    inputNumber.value = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle.Default.copy(fontSize = 20.sp, color = Color(0xFFcaf0f8))
            )
            Button(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.9f),
                colors = ButtonDefaults.buttonColors(Color(0xFFC7F9CC)),
                onClick = {
                    if (inputNumber.value.toIntOrNull() != null) {
                        onButtonClicked(inputNumber.value)
                    }
                }) {
                Text(
                    modifier = Modifier,
                    color = Color(0xff5e60ce),
                    text = "Calculate",
                    fontSize = 20.sp,
                    fontFamily = Asul,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
