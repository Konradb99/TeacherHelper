package com.example.Teacher.viewModel.viewModelFactories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.Teacher.viewModel.MarksHandler
import java.lang.IllegalArgumentException

class MarksHandlerFactory (private val application: Application): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MarksHandler::class.java)){
            return MarksHandler(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
