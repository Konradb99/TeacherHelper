package com.example.Teacher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.Teacher.database.HelperDAO
import com.example.Teacher.database.HelperDatabase
import com.example.Teacher.entities.Lecture
import com.example.Teacher.entities.LectureMarks
import com.example.Teacher.entities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentHandler(application: Application): AndroidViewModel(application)  {
    private val helperDAO : HelperDAO
    var student: Student
    val students: LiveData<List<Student>>
    var currentStudent: Student
    var currentStudentLectures: LiveData<List<LectureMarks>>
    init{
        helperDAO= HelperDatabase.getInstance(application).helperDAO
        students = helperDAO.getAllStudents()

        currentStudentLectures = helperDAO.getAllAverages()
        currentStudent = Student(0, "", "")
        student = Student(0, "", "")
    }
    fun AddStudent(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            helperDAO.InsertStudent(student)
        }
    }
    fun deleteStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO){
            helperDAO.DeleteStudent(student)
        }
    }

    fun getAverages(student: Long, lecture: Long): LiveData<List<LectureMarks>>{
        //currentStudentLectures = helperDAO.getAverages(student, lecture)
        return currentStudentLectures
    }
}