package com.example.Teacher.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.Teacher.entities.Lecture
import com.example.Teacher.entities.Student

@Dao
interface HelperDAO {
    @Insert
    fun InsertStudent(student: Student)

    @Insert
    fun InsertLecture(lecture: Lecture)

    @Delete
    fun DeleteStudent(student: Student)

    @Delete
    fun DeleteLecture(lecture: Lecture)

    @Query("SELECT * FROM studentsTable")
    fun getAllStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM classesTable")
    fun getAllClasses(): LiveData<List<Lecture>>

}