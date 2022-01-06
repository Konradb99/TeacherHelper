package com.example.Teacher.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.Teacher.entities.Groups
import com.example.Teacher.entities.Lecture
import com.example.Teacher.entities.Marks
import com.example.Teacher.entities.Student

@Dao
interface HelperDAO {
    @Insert
    fun InsertStudent(student: Student)

    @Insert
    fun InsertLecture(lecture: Lecture)

    @Insert
    fun InsertStudentToGroup(group: Groups)

    @Insert
    fun InsertMark(mark: Marks)

    @Delete
    fun DeleteStudent(student: Student)

    @Delete
    fun DeleteLecture(lecture: Lecture)

    @Query("DELETE FROM groupsTable WHERE groupsTable.`id.p` = :lecture")
    fun removeGroup(lecture: Long)

    @Query("SELECT * FROM studentsTable")
    fun getAllStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM classesTable")
    fun getAllClasses(): LiveData<List<Lecture>>

    @Query("SELECT * FROM groupsTable")
    fun getAllStudentsInGroup(): LiveData<List<Groups>>

    @Query("SELECT * FROM marksTable")
    fun getAllMarks(): LiveData<List<Marks>>

    @Query("SELECT * FROM studentsTable INNER JOIN groupsTable ON studentsTable.indexNumber = groupsTable.`id.s` WHERE groupsTable.`id.p` = :lecture")
    fun getStudentsInLecture(lecture: Long): LiveData<List<Student>>

    @Query("SELECT * FROM groupsTable")
    fun getAllAddedStudents(): List<Groups>

    @Query("SELECT * FROM marksTable WHERE marksTable.idLecture = :lecture AND marksTable.idStudent = :student")
    fun getMarksForStudent(lecture: Long, student: Long): LiveData<List<Marks>>

    @Update
    fun updateLecture(lecture: Lecture)

}