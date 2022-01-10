package com.example.Teacher.database

import androidx.lifecycle.LiveData
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

    @Query("SELECT * FROM averagesTable")
    fun getAllAverages(): LiveData<List<Averages>>

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

    @Query("SELECT T.* FROM (SELECT id, idStudent, idLecture, lectureName, Average, Max(AverageValue) FROM averagesTable GROUP BY idLecture) as A INNER JOIN averagesTable as T ON A.id = T.id WHERE T.idStudent = :student")
    fun getAverages(student: Long): LiveData<List<Averages>>

    @Update
    fun updateLecture(lecture: Lecture)

    @Update
    fun updateStudent(student: Student)

    @Query("DELETE FROM studentsTable")
    fun clearStudents()

    @Query("DELETE FROM classesTable")
    fun clearLectures()

    @Query("DELETE FROM marksTable")
    fun clearMarks()

    @Query("DELETE FROM averagesTable")
    fun clearAverages()

    @Query("DELETE FROM groupsTable")
    fun clearGroups()


    @Query("SELECT COUNT(*) FROM studentsTable WHERE studentsTable.indexNumber = :stud")
    fun checkIfStudentExists(stud: Long): Int


}