package com.example.Teacher.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="marksTable")
data class Marks(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var markID: Long = 0L,
    @ColumnInfo(name="idLecture")
    var lectureID: Long = 0L,
    @ColumnInfo(name="idStudent")
    var studentID: Long = 0L,
    @ColumnInfo(name="type")
    var markType: String,
    @ColumnInfo(name="mark")
    var mark: Double,
    @ColumnInfo(name="weight")
    var weight: Int
)