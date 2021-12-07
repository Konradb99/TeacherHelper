package com.example.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "studentsTable")
data class Student(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="indexNumber")
    var userID: Long = 0L,
    @ColumnInfo(name="firstName")
    var userFirstName: String,
    @ColumnInfo(name="lastName")
    var userLastName: String

)