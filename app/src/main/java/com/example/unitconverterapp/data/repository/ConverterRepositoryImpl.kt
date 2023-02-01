package com.example.unitconverterapp.data.repository

import com.example.unitconverterapp.data.database.ConverterDAO
import com.example.unitconverterapp.data.model.ConversionResult
import kotlinx.coroutines.flow.Flow

class ConverterRepositoryImpl(private val converterDAO: ConverterDAO): ConverterRepository {
    override fun getAllConversionsResult(): Flow<List<ConversionResult>> =
        converterDAO.getAllConversionResults()

    override suspend fun insertConversionResult(conversionResult: ConversionResult) {
        converterDAO.insertConversionResult(conversionResult)
    }

    override suspend fun deleteConversionResult(conversionResult: ConversionResult) {
        converterDAO.deleteConversionResult(conversionResult)
    }

    override suspend fun deleteAll() {
        converterDAO.deleteAll()
    }
}