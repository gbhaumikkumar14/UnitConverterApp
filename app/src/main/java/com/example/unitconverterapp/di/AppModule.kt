package com.example.unitconverterapp.di

import android.app.Application
import androidx.room.Room
import com.example.unitconverterapp.data.database.ConverterDatabase
import com.example.unitconverterapp.data.repository.ConverterRepository
import com.example.unitconverterapp.data.repository.ConverterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideConverterDatabase(application: Application): ConverterDatabase {
        return Room.databaseBuilder(
            application,
            ConverterDatabase::class.java,
            "conversion_history_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideConverterRepository(converterDatabase: ConverterDatabase): ConverterRepository {
        return ConverterRepositoryImpl(converterDAO = converterDatabase.converterDAO)
    }
}