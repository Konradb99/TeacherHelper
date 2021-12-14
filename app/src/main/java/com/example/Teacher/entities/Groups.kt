package com.example.Teacher.entities

import androidx.room.ColumnInfo

data class Groups(
    @ColumnInfo(name="id.p")
    var classID: Long = 0L,
    @ColumnInfo(name="id.s")
    var studetnID: Long = 0L
)