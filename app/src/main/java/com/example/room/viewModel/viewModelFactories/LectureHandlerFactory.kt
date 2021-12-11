package com.example.room.viewModel.viewModelFactories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.room.viewModel.LectureHandler
import java.lang.IllegalArgumentException

class LectureHandlerFactory (private val application: Application):ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LectureHandler::class.java)){
            return LectureHandler(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}

