package com.example.room.viewModelFactories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.room.viewModels.AddClassesViewModel
import java.lang.IllegalArgumentException

class AddClassesViewModelFactory (private val application: Application):ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddClassesViewModel::class.java)){
            return AddClassesViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
