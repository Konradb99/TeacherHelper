package com.example.room.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room.database.HelperDAO
import com.example.room.database.HelperDatabase
import com.example.room.entities.Lecture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClassesListViewModel(application: Application):AndroidViewModel(application) {
    private val helperDAO : HelperDAO = HelperDatabase.getInstance(application).helperDAO
    val lectures:LiveData<List<Lecture>> = helperDAO.getAllClasses()

    fun deleteLecture(lecture: Lecture){
        viewModelScope.launch(Dispatchers.IO){
            helperDAO.DeleteLecture(lecture)
        }
    }
}