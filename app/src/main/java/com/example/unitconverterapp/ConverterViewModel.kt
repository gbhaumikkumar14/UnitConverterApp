package com.example.unitconverterapp

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unitconverterapp.data.model.Conversion
import com.example.unitconverterapp.data.model.ConversionResult
import com.example.unitconverterapp.data.repository.ConverterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConverterViewModel(private val repository: ConverterRepository): ViewModel()
{
    val selectedConversion: MutableState<Conversion?> = mutableStateOf(null)
    val inputText: MutableState<String> = mutableStateOf("")
    val typedValue = mutableStateOf("0.0")

    val conversionHistory = repository.getAllConversionsResult()

    fun insertConversionResult(message1: String, message2: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertConversionResult(
                ConversionResult(id = 0, message1 = message1, message2 = message2)
            )
        }
    }

    fun deleteConversionResult(conversionResult: ConversionResult){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteConversionResult(conversionResult)
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun conversionTypesList() = listOf(
        Conversion(
            id = 1,
            description = R.string.pounds_to_kilograms.toString(),
            convertFrom = R.string.pound.toString(),
            convertTo = R.string.kilogram.toString(),
            multiplyBy = 0.4535
        ),
        Conversion(
            id = 2,
            description = R.string.kilograms_to_pounds.toString(),
            convertFrom = R.string.kilogram.toString(),
            convertTo = R.string.pound.toString(),
            multiplyBy = 2.2046
        ),
        Conversion(
            id = 3,
            description = R.string.yards_to_meters.toString(),
            convertFrom = R.string.yard.toString(),
            convertTo = R.string.meter.toString(),
            multiplyBy = 0.9144),
        Conversion(
            id = 4,
            description = R.string.meters_to_yards.toString(),
            convertFrom = R.string.meter.toString(),
            convertTo = R.string.yard.toString(),
            multiplyBy = 1.0936),
        Conversion(
            id = 5,
            description = R.string.miles_to_kilometers.toString(),
            convertFrom = R.string.mile.toString(),
            convertTo = R.string.kilometer.toString(),
            multiplyBy = 1.6093),
        Conversion(
            id = 6,
            description = R.string.kilometers_to_miles.toString(),
            convertFrom = R.string.kilometer.toString(),
            convertTo = R.string.mile.toString(),
            multiplyBy = 0.6213)
    )
}