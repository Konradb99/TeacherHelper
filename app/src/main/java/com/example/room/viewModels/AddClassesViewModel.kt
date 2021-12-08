package com.example.room.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.room.database.HelperDAO
import com.example.room.database.HelperDatabase
import com.example.room.entities.Lecture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddClassesViewModel(application: Application):AndroidViewModel(application) {
    private val helperDAO: HelperDAO
    init{
        helperDAO= HelperDatabase.getInstance(application).helperDAO
    }
    fun AddClass(lecture: Lecture) {
        viewModelScope.launch(Dispatchers.IO) {
            helperDAO.InsertLecture(lecture)
        }
    }
}