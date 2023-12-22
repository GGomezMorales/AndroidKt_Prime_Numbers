package com.ggomezmorales.primenumbers.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ggomezmorales.primenumbers.ui.theme.Asul

@Composable
fun InputField(
    title: String = "Title",
    onButtonClicked: (String) -> Unit
) {
    var inputNumber by remember { mutableStateOf("") }
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
            text = title,
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
                value = inputNumber,
                onValueChange = {
                    inputNumber = it
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
                    if (inputNumber.toIntOrNull() != null) {
                        onButtonClicked(inputNumber)
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