package com.example.unitconverterapp.composable

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.unitconverterapp.R
import com.example.unitconverterapp.data.model.Conversion

private const val TAG = "ConverterTypesSpinner"

@Composable
fun ConverterTypesSpinner(modifier: Modifier, conversionTypesList: List<Conversion>, convert : (Conversion) -> Unit){
    var displayText by rememberSaveable { mutableStateOf(R.string.select_the_conversion_type.toString()) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }

    val icon = if(expanded)
        Icons.Default.KeyboardArrowUp
    else
        Icons.Default.KeyboardArrowDown

    Column {
        OutlinedTextField(
            value = stringResource(id = displayText.toInt()),
            onValueChange = { displayText = it },
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            ),
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned { cordinates ->
                    textFieldSize = cordinates.size.toSize()
                },
            label = { Text(text = stringResource(id = R.string.conversion_type)) },
            trailingIcon = {
                Icon(icon, contentDescription = "icon",
                    modifier.clickable { expanded = !expanded })
            },
            readOnly = true
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            conversionTypesList.forEach { conversion ->
                Log.d(TAG, "ConverterTypesSpinner: $conversion")
                DropdownMenuItem(onClick = {
                    displayText = conversion.description
                    expanded = false
                    convert(conversion)
                }) {
                    Text(
                        text = stringResource(id = conversion.description.toInt()),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}