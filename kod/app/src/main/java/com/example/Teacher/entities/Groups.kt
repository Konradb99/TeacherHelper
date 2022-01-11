package com.example.Teacher.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="groupsTable")
data class Groups(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var groupUserID: Long = 0L,
    @ColumnInfo(name="id.p")
    var classID: Long = 0L,
    @ColumnInfo(name="id.s")
    var studentID: Long = 0L
)