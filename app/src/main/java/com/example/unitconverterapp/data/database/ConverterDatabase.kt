package com.example.unitconverterapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.unitconverterapp.data.model.ConversionResult

@Database(entities = [ConversionResult::class], version = 1, exportSchema = false)
abstract class ConverterDatabase: RoomDatabase() {
    abstract val converterDAO: ConverterDAO
}