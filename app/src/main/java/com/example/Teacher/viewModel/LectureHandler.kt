package com.example.Teacher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.Teacher.database.HelperDAO
import com.example.Teacher.database.HelperDatabase
import com.example.Teacher.entities.Lecture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class LectureHandler(application: Application):AndroidViewModel(application) {
    private val helperDAO: HelperDAO
    var lectureName:String? = ""
    var lecture:Lecture
    val lectures: LiveData<List<Lecture>>
    init{
        helperDAO= HelperDatabase.getInstance(application).helperDAO
        lectures = helperDAO.getAllClasses()
        lecture = Lecture(0, "", "", "", "")
    }
    fun AddLecture(lecture: Lecture) {
        viewModelScope.launch(Dispatchers.IO) {
            helperDAO.InsertLecture(lecture)
        }
    }
    fun DeleteLecture(lecture: Lecture){
        viewModelScope.launch(Dispatchers.IO){
            helperDAO.DeleteLecture(lecture)
        }
    }

    fun UpdateLecture(lecture: Lecture){
        viewModelScope.launch(Dispatchers.IO) {
            helperDAO.updateLecture(lecture)
        }
    }



    fun check_hour_start(hour_first: String, hour_second: String):Boolean{
        var isLower:Boolean = false
        try{
            val time1: Date = SimpleDateFormat("HH:mm").parse(hour_first)
            val time2: Date = SimpleDateFormat("HH:mm").parse(hour_second)
            if(time1.after(time2)){
                isLower = true
            }
        }catch(e: ParseException){
            e.printStackTrace()
        }
        return isLower
    }

     fun check_hour_end(hour_first: String, hour_second: String):Boolean{
        var isHigher:Boolean = false
        try{
            val time1: Date = SimpleDateFormat("HH:mm").parse(hour_first)
            println(time1)
            val time2: Date = SimpleDateFormat("HH:mm").parse(hour_second)
            println(time2)
            if(time1.before(time2)){
                isHigher = true
            }
        }catch(e: ParseException){
            e.printStackTrace()
        }
        return isHigher
    }
}