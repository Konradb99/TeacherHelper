package com.example.room.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room.database.HelperDAO
import com.example.room.database.HelperDatabase
import com.example.room.entities.Lecture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LectureHandler(application: Application):AndroidViewModel(application) {
    private val helperDAO: HelperDAO
    var lectureName:String? = ""
    var lecture:Lecture
    val lectures: LiveData<List<Lecture>>
    init{
        helperDAO= HelperDatabase.getInstance(application).helperDAO
        lectures = helperDAO.getAllClasses()
        lecture = Lecture(0, "")
    }
    fun AddClass(lecture: Lecture) {
        viewModelScope.launch(Dispatchers.IO) {
            helperDAO.InsertLecture(lecture)
        }
    }
    fun deleteLecture(lecture: Lecture){
        viewModelScope.launch(Dispatchers.IO){
            helperDAO.DeleteLecture(lecture)
        }
    }
}