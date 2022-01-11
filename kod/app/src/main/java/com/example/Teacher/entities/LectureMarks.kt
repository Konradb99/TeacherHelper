package com.example.Teacher.entities

import androidx.room.ColumnInfo

data class LectureMarks(
    @ColumnInfo(name="id")
    var lectureID: Long,
    @ColumnInfo(name="name")
    var lectureName: String,
    @ColumnInfo(name="Average")
    var Average: Double
)