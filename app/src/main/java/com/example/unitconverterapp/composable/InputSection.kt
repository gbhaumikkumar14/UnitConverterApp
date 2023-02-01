package com.example.unitconverterapp.composable

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverterapp.data.model.Conversion
import com.example.unitconverterapp.R

@Composable
fun InputSection(
    modifier: Modifier = Modifier, // Optional
    conversion: Conversion,
    inputText : MutableState<String>,
    context: Context = LocalContext.current,
    calculate: (String) -> Unit
){
    Column(modifier = modifier.padding(top = 20.dp)) {
        Row(modifier = modifier.fillMaxWidth()) {
            TextField(
                value = inputText.value,
                onValueChange ={
                    inputText.value = it
                },
                modifier = modifier
                    .fillMaxWidth(0.65F),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface.copy(
                        alpha = 0.3F
                    )
                ),
                textStyle = TextStyle(
                    color = Color.DarkGray,
                    fontSize = 30.sp
                )
            )
            Text(
                text = stringResource(id = conversion.convertFrom.toInt()),
                fontSize = 24.sp,
                modifier = modifier
                    .padding(start = 8.dp, top = 24.dp)
                    .fillMaxWidth(0.35F)
            )
        }
        Spacer(modifier = modifier.height(16.dp))
        OutlinedButton(
            onClick = {
                if(inputText.value != "") {
                    calculate(inputText.value)
                }else{
                    Toast.makeText(context, R.string.error_message, Toast.LENGTH_SHORT).show()
                }
            },
            modifier = modifier.fillMaxWidth(1F)
        ) {
            Text(
                text = stringResource(id = R.string.convert),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}