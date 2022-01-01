package com.example.Teacher.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.Teacher.entities.Groups
import com.example.Teacher.entities.Lecture
import com.example.Teacher.entities.Student

@Dao
interface HelperDAO {
    @Insert
    fun InsertStudent(student: Student)

    @Insert
    fun InsertLecture(lecture: Lecture)

    @Insert
    fun InsertStudentToGroup(group: Groups)

    @Delete
    fun DeleteStudent(student: Student)

    @Delete
    fun DeleteLecture(lecture: Lecture)

    @Query("SELECT * FROM studentsTable")
    fun getAllStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM classesTable")
    fun getAllClasses(): LiveData<List<Lecture>>

    @Query("SELECT * FROM groupsTable")
    fun getAllStudentsInGroup(): LiveData<List<Groups>>

    @Update
    fun updateLecture(lecture: Lecture)

}