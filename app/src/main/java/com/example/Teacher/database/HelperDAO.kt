package com.example.Teacher.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.Teacher.entities.*

@Dao
interface HelperDAO {
    @Insert
    fun InsertStudent(student: Student)

    @Insert
    fun InsertLecture(lecture: Lecture)

    @Insert
    fun InsertStudentToGroup(group: Groups)

    @Insert
    fun InsertAverage(average: Averages)

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

    @Query("SELECT classesTable.id, classesTable.name, averagesTable.Average as Average FROM classesTable INNER JOIN groupsTable ON classesTable.id = groupsTable.`id.p` INNER JOIN averagesTable ON averagesTable.idLecture = groupsTable.`id.p`")
    fun getAllAverages(): LiveData<List<LectureMarks>>

    @Query("SELECT * FROM averagesTable")
    fun getAllAverage(): LiveData<List<Averages>>

    @Query("SELECT COUNT(*) FROM averagesTable WHERE averagesTable.idStudent = :stud AND averagesTable.idLecture = :lect")
    fun checkAverages(stud: Long, lect: Long): Int

    @Query("SELECT * FROM studentsTable INNER JOIN groupsTable ON studentsTable.indexNumber = groupsTable.`id.s` WHERE groupsTable.`id.p` = :lecture")
    fun getStudentsInLecture(lecture: Long): LiveData<List<Student>>

    @Query("SELECT * FROM groupsTable")
    fun getAllAddedStudents(): List<Groups>

    @Query("SELECT * FROM marksTable WHERE marksTable.idLecture = :lecture AND marksTable.idStudent = :student")
    fun getMarksForStudent(lecture: Long, student: Long): LiveData<List<Marks>>

    @Query("SELECT classesTable.id, classesTable.name, averagesTable.Average as Average FROM classesTable INNER JOIN groupsTable ON classesTable.id = groupsTable.`id.p` INNER JOIN averagesTable ON averagesTable.idLecture = groupsTable.`id.p` WHERE averagesTable.idStudent = :student AND averagesTable.idLecture = :lecture AND groupsTable.`id.s` = :student")
    fun getAverages(student: Long, lecture: Long): LiveData<List<LectureMarks>>

    @Update
    fun updateLecture(lecture: Lecture)

}