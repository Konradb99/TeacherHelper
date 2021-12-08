package com.example.room.viewModelFactories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.room.viewModels.ClassesListViewModel
import java.lang.IllegalArgumentException

class ClassesListViewModelFactory(private val application: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ClassesListViewModel::class.java)){
            return ClassesListViewModel(application) as T
        }
        throw IllegalArgumentException("Unkown ViewModel Class")
    }
}