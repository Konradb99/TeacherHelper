package com.example.Teacher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.Teacher.database.HelperDAO
import com.example.Teacher.database.HelperDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsHandler(application: Application): AndroidViewModel(application) {
    private val helperDAO : HelperDAO
    init{
        helperDAO = HelperDatabase.getInstance(application).helperDAO
    }

    fun clearLectures(){
        viewModelScope.launch(Dispatchers.IO) {
            helperDAO.clearLectures()
        }
    }

    fun clearStudents(){
        viewModelScope.launch(Dispatchers.IO) {
            helperDAO.clearStudents()
        }
    }

    fun clearMarks(){
        viewModelScope.launch(Dispatchers.IO) {
            helperDAO.clearMarks()
        }
    }

    fun clearDataBase(){
        viewModelScope.launch(Dispatchers.IO) {
            helperDAO.clearLectures()
            helperDAO.clearStudents()
            helperDAO.clearMarks()
            helperDAO.clearAverages()
            helperDAO.clearGroups()
        }
    }
}