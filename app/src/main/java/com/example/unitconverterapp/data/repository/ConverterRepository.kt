package com.example.unitconverterapp.data.repository

import com.example.unitconverterapp.data.model.ConversionResult
import kotlinx.coroutines.flow.Flow

interface ConverterRepository {
    fun getAllConversionsResult(): Flow<List<ConversionResult>>

    suspend fun insertConversionResult(conversionResult: ConversionResult)

    suspend fun deleteConversionResult(conversionResult: ConversionResult)

    suspend fun deleteAll()
}