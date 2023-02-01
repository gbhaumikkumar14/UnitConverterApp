package com.example.unitconverterapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversion_history_table")
data class ConversionResult(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "result_id")
    val id: Int,

    @ColumnInfo(name = "result_message1")
    val message1: String,

    @ColumnInfo(name = "result_message2")
    val message2: String
)
