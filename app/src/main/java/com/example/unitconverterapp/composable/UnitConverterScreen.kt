package com.example.unitconverterapp.composable

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unitconverterapp.data.model.Conversion
import com.example.unitconverterapp.ConverterViewModel
import com.example.unitconverterapp.ConverterViewModelFactory
import java.math.RoundingMode
import java.text.DecimalFormat

private const val TAG = "UnitConverterScreen"

@Composable
fun UnitConverterScreen(
    modifier: Modifier,
    factory: ConverterViewModelFactory,
    converterViewModel: ConverterViewModel = viewModel(factory= factory),
    context: Context = LocalContext.current)
{
    var toSave by remember {
        mutableStateOf(false)
    }
    val list = converterViewModel.conversionTypesList()
    val historyList = converterViewModel.conversionHistory.collectAsState(initial = emptyList())

    val configuration = LocalConfiguration.current

    Column(modifier = modifier.padding(16.dp)) {
        ConverterTypesSpinner(modifier = modifier, list){
            Log.d(TAG, "UnitConverterScreen: ")
            converterViewModel.selectedConversion.value = it
            converterViewModel.typedValue.value = "0.0"
        }
        converterViewModel.selectedConversion.value?.let {
            InputSection(modifier = modifier, conversion = it, inputText = converterViewModel.inputText){ input ->
                converterViewModel.typedValue.value = input
                toSave = true
            }
        }
        if(converterViewModel.typedValue.value != "0.0"){
            val input = converterViewModel.typedValue.value.toDouble()
            val multiply = converterViewModel.selectedConversion.value!!.multiplyBy
            val result = input * multiply
            // Rounding off the result to 4 decimal places
            val df = DecimalFormat("#.####")
            df.roundingMode = RoundingMode.DOWN
            val roundedResult = df.format(result)

            val message1 = "${converterViewModel.typedValue.value} ${context.getString(converterViewModel.selectedConversion.value!!.convertFrom.toInt())} is equal to"
            val message2 = "$roundedResult ${context.getString(converterViewModel.selectedConversion.value!!.convertTo.toInt())}"
            if(toSave) {
                converterViewModel.insertConversionResult(message1, message2)
                toSave = false
            }
            ResultSection(modifier = modifier, message1 = message1, message2 = message2)
        }

        HistorySection(
            modifier = modifier,
            historyList = historyList,
            onCloseTask = { conversionResult ->
                converterViewModel.deleteConversionResult(conversionResult)
            },
            onClearAll = {
                converterViewModel.deleteAll()
            })
    }
}