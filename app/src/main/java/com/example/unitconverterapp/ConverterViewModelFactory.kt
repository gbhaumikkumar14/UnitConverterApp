package com.example.unitconverterapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.unitconverterapp.data.repository.ConverterRepository
import javax.inject.Inject

class ConverterViewModelFactory @Inject constructor(
    private val repository: ConverterRepository): ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConverterViewModel(repository) as T
    }
}