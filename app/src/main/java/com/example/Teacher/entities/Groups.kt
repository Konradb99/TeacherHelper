package com.example.Teacher.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName="groupsTable")
data class Groups(
    @ColumnInfo(name="id.p")
    var classID: Long = 0L,
    @ColumnInfo(name="id.s")
    var studentID: Long = 0L
)