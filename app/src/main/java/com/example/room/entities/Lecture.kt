package com.example.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="classesTable")
data class Lecture(
@PrimaryKey(autoGenerate = true)
@ColumnInfo(name="id")
var classID: Long = 0L,
@ColumnInfo(name="name")
var className: String
)