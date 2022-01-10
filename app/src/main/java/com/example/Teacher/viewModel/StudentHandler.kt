package com.example.Teacher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.Teacher.database.HelperDAO
import com.example.Teacher.database.HelperDatabase
import com.example.Teacher.entities.Averages
import com.example.Teacher.entities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentHandler(application: Application): AndroidViewModel(application)  {
    private val helperDAO : HelperDAO
    var student: Student
    val students: LiveData<List<Student>>
    var currentStudent: Student
    var currentStudentLectures: LiveData<List<Averages>>
    var existsStudent: Boolean
    init{
        helperDAO= HelperDatabase.getInstance(application).helperDAO
        students = helperDAO.getAllStudents()
        existsStudent = false
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
    fun updateStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            helperDAO.updateStudent(student)
        }
    }

    fun checkIfExists(stud: Student): Boolean{
        if(students.value != null){
            for(i in students.value!!){
                if(i.userID == stud.userID){
                    println("Istnieje")
                    existsStudent=true
                    break
                }
                else{
                    existsStudent=false
                    println("Nie istnieje")
                }
            }
        }
        else{
            existsStudent = false
        }
        return existsStudent
    }

    fun getAverages(student: Long, lecture: Long): LiveData<List<Averages>>{
        currentStudentLectures = helperDAO.getAverages(student)
        return currentStudentLectures
    }
}