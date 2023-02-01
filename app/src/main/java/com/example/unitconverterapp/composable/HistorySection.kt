package com.example.unitconverterapp.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverterapp.R
import com.example.unitconverterapp.data.model.ConversionResult

@Composable
fun HistorySection(
    modifier: Modifier,
    historyList: State<List<ConversionResult>>,
    onCloseTask: (ConversionResult) -> Unit,
    onClearAll: () -> Unit)
{
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(top = 32.dp)) {
        if(historyList.value.isNotEmpty()) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.history), color = Color.Gray)
                OutlinedButton(
                    onClick = { onClearAll() },
                    border = BorderStroke(width = 0.5.dp, color = Color.Red)
                ) {
                    Text(text = stringResource(id = R.string.clear_all), color = Color.Red)
                }
            }
            Spacer(modifier = modifier.height(8.dp))
        }
        LazyColumn {
            items(
                items = historyList.value,
                key = { item -> item.id }
            ) { item ->
                HistoryItem(
                    modifier = modifier,
                    message1 = item.message1,
                    message2 = item.message2,
                    onClose = { onCloseTask(item) })
            }
        }
    }
}

@Composable
private fun HistoryItem(modifier: Modifier, message1: String, message2: String, onClose: () -> Unit){
    Row(modifier = modifier
        .fillMaxWidth()
        .border(border = BorderStroke(width = 0.5.dp, color = Color.Gray)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = message1, fontSize = 20.sp)
            Text(text = message2, fontSize = 20.sp, color = Color.Blue, fontWeight = FontWeight.Bold)
        }
        IconButton(onClick = { onClose() }) {
            Icon(Icons.Filled.Close, contentDescription = "icon")
        }
    }
}