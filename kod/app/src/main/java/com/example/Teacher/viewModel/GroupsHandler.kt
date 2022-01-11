package com.example.Teacher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.Teacher.database.HelperDAO
import com.example.Teacher.database.HelperDatabase
import com.example.Teacher.entities.Groups
import com.example.Teacher.entities.Lecture
import com.example.Teacher.entities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupsHandler(application: Application): AndroidViewModel(application) {
    private val helperDAO : HelperDAO
    var studentsSelected: MutableList<Long>
    var currentLecture: Lecture
    var allStudents: LiveData<List<Groups>>
    var studentsInGroup: LiveData<List<Student>>
    init{
        helperDAO= HelperDatabase.getInstance(application).helperDAO
        studentsSelected = arrayListOf<Long>()
        allStudents = helperDAO.getAllStudentsInGroup()
        studentsInGroup = helperDAO.getAllStudents()
        currentLecture = Lecture(0, "", "", "", "")
    }
    fun AddStudent(group: Groups) {
        viewModelScope.launch(Dispatchers.IO) {
            helperDAO.InsertStudentToGroup(group)
        }
    }

    fun getStudentsInGroup(lecture: Long): LiveData<List<Student>> {
        studentsInGroup = helperDAO.getStudentsInLecture(lecture)
        return studentsInGroup
    }

    fun removeGroup(lecture: Long){
        viewModelScope.launch(Dispatchers.IO) {
            helperDAO.removeGroup(lecture)
        }
    }
}