package com.example.unitconverterapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.unitconverterapp.data.model.ConversionResult
import kotlinx.coroutines.flow.Flow

@Dao
interface ConverterDAO {

    @Insert
    suspend fun insertConversionResult(conversionResult: ConversionResult)

    @Delete
    suspend fun deleteConversionResult(conversionResult: ConversionResult)

    @Query(value = "DELETE FROM conversion_history_table")
    suspend fun deleteAll()

    @Query(value = "SELECT * FROM conversion_history_table")
    fun getAllConversionResults(): Flow<List<ConversionResult>>
}