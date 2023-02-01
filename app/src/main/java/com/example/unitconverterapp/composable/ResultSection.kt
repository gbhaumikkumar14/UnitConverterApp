package com.example.unitconverterapp.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultSection(modifier: Modifier, message1: String, message2: String){
    Card(
        elevation = 16.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        Column(modifier = modifier
            .padding(all = 8.dp)
        ) {
            Text(
                text = message1,
                fontSize = 16.sp,
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = message2,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primaryVariant,
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}