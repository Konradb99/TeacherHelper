package com.example.Teacher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.Teacher.database.HelperDAO
import com.example.Teacher.database.HelperDatabase
import com.example.Teacher.entities.Averages
import com.example.Teacher.entities.Marks
import com.example.Teacher.entities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MarksHandler(application: Application): AndroidViewModel(application) {
    private val helperDAO : HelperDAO
    var student: Student
    val allMarks : LiveData<List<Marks>>
    var currentStudentMarks: LiveData<List<Marks>>
    var avg : MutableLiveData<Int>


    init{
        helperDAO= HelperDatabase.getInstance(application).helperDAO
        allMarks = helperDAO.getAllMarks()
        currentStudentMarks = helperDAO.getAllMarks()
        avg = MutableLiveData<Int>(0)
        student = Student(0, "", "")
    }


    fun AddMark(mark: Marks) {
        viewModelScope.launch(Dispatchers.IO) {
            helperDAO.InsertMark(mark)
        }
    }

    fun AddAverage(mark: Averages){
        viewModelScope.launch(Dispatchers.IO) {
            helperDAO.InsertAverage(mark)
        }
    }

    fun getCurrentStudentMarks(lecture: Long, student: Long): LiveData<List<Marks>>  {
        currentStudentMarks = helperDAO.getMarksForStudent(lecture, student)
        return currentStudentMarks
    }


}