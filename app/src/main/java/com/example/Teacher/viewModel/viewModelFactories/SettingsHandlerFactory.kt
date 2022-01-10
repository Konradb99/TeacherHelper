package com.example.Teacher.viewModel.viewModelFactories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.Teacher.viewModel.MarksHandler
import com.example.Teacher.viewModel.SettingsHandler
import java.lang.IllegalArgumentException

class SettingsHandlerFactory(private val application: Application): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SettingsHandler::class.java)){
            return SettingsHandler(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}