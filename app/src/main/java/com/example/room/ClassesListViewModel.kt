package com.example.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.room.entities.Lecture

class ClassesListViewModel(application: Application):AndroidViewModel(application) {
    private val helperDAO : HelperDAO = HelperDatabase.getInstance(application).helperDAO
    val lectures:LiveData<List<Lecture>> = helperDAO.getAllClasses()

}