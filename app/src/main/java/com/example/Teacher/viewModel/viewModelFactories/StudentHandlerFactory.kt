package com.example.Teacher.viewModel.viewModelFactories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.Teacher.viewModel.LectureHandler
import com.example.Teacher.viewModel.StudentHandler
import java.lang.IllegalArgumentException

class StudentHandlerFactory(private val application: Application): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StudentHandler::class.java)){
            return StudentHandler(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}