package com.example.Teacher.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "averagesTable")
data class Averages(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L,
    @ColumnInfo(name="idStudent")
    var studentID: Long = 0L,
    @ColumnInfo(name="idLecture")
    var lectureID: Long = 0L,
    @ColumnInfo(name="Average")
    var avg: Double
)